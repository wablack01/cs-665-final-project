/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: Server.java
 * Description: This class is responsible for the methods and attributes of a Server
 */

package edu.bu.met.cs665.server;

import edu.bu.met.cs665.client.Client;
import edu.bu.met.cs665.client.ClientTier;
import edu.bu.met.cs665.client.Request;
import edu.bu.met.cs665.repositories.HashDataStore;
import edu.bu.met.cs665.repositories.DataStore;

/**
 * The server class represents a resource server in a Client/Server architecture
 * @param <T> The type of resource held by this server.
 */
public class Server<T> {
  private final DataStore<String, T> dataStore = new HashDataStore<>();
  private final RequestCounter requestCounter;
  private final int standardRateLimit;

  /**
   * Instantiates a server with the provided parameters.
   * @param requestCounter the request counter to track a client's requests.
   * @param timer timer to manage the throttling period and resets.
   * @param standardRateLimit the maximum number of requests per second for standard tier Clients
   */
  public Server(RequestCounter requestCounter, Throttler timer, int standardRateLimit) {
    this.requestCounter = requestCounter;
    this.standardRateLimit = standardRateLimit;
    timer.start();
  }

  /**
   * Retreive a resource from the data store.
   * @param request the request holding the resource name.
   * @return Response object with resource, status, and message.
   */
  public Response<T> getResource(Request<T> request) {
    Client client = request.getClient();
    long count = requestCounter.getCount(client.getClientName());
    String message;

    if (client.getTier() == ClientTier.STANDARD && count >= standardRateLimit) {
      message = String.format(
        "SERVER: Rejected request from [%s]. Standard tier rate limit exceeded",
        client.getClientName()
      );
      System.err.println(message);
      return new Response<>(null, Response.FAIL, message);
    }

    T resource = dataStore.get(request.getResourceName());
    if (resource == null) {
      message = String.format(
        "SERVER: Processed request #%d from [%s]. [%s] not found",
        count + 1,
        client.getClientName(),
        request.getResourceName()
      );
      System.err.println(message);
      requestCounter.incrementCount(client.getClientName());
      return new Response<>(null, Response.FAIL, message);
    }

    message = String.format(
      "SERVER: Processed request #%d from [%s]. Returning [%s]",
      count + 1,
      client.getClientName(),
      request.getResourceName()
    );
    System.out.println(message);
    requestCounter.incrementCount(client.getClientName());
    return new Response<>(resource, Response.SUCCESS, message);
  }

  /**
   * Post a resource to the data store.
   * @param request the request holding the resource to post.
   * @return Response object with resource, status, and message.
   */
  public Response<T> postResource(Request<T> request) {
    Client<T> client = request.getClient();
    String message;

    if (client.getTier() == ClientTier.ADMIN) {
      dataStore.put(request.getResource().toString(), request.getResource());
      message = String.format(
        "SERVER: Processed request from [%s]. Saving %s.",
        client.getClientName(),
        request.getResource().toString()
      );
      System.out.println(message);
      return new Response<>(request.getResource(), Response.SUCCESS, message);
    }

    message = String.format(
      "SERVER: Rejected request from [%s]. Unauthorized access.",
      client.getClientName()
    );
    System.err.println(message);
    return new Response<>(null, Response.FAIL, message);
  }

  /**
   * Delete a resource from the data store.
   * @param request the request holding the resource to delete.
   * @return Response object with resource, status, and message.
   */
  public Response<T> deleteResource(Request<T> request) {
    Client<T> client = request.getClient();
    String message;

    if (client.getTier() == ClientTier.ADMIN) {
      dataStore.delete(request.getResourceName());
      message = String.format(
          "SERVER: Processed request from [%s]. Deleting %s.",
          client.getClientName(),
          request.getResourceName()
      );
      System.out.println(message);
      return new Response<>(request.getResource(), Response.SUCCESS, message);
    }

    message = String.format(
            "SERVER: Rejected request from [%s]. Unauthorized access.",
            client.getClientName()
    );
    System.err.println(message);
    return new Response<>(null, Response.FAIL, message);
  }
}