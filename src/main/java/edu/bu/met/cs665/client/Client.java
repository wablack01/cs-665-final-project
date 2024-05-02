/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: Client.java
 * Description: This class is responsible for the methods and attributes of a Client.
 */

package edu.bu.met.cs665.client;

import edu.bu.met.cs665.callbacks.Callback;
import edu.bu.met.cs665.server.RequestCounter;
import edu.bu.met.cs665.server.Response;
import edu.bu.met.cs665.server.Server;

import java.util.Optional;

/**
 * The Client class represents a Client in a Client/Server architecture.
 * @param <T> The Class type of the resources the Client will handle.
 */
public class Client<T> {
  private final String clientName;
  private final ClientTier tier;

  public Client(String clientName, ClientTier tier, RequestCounter requestCounter) {
    this.clientName = clientName;
    this.tier = tier;
    requestCounter.addClient(clientName);
  }

  /**
   * Request a resource from a server.
   * @param server The server to request the resource from.
   * @param resourceName The name of the resource being requested.
   * @param responseHandler Code to execute after resource is received.
   */
  public T initiateGetRequest(Server<T> server, String resourceName, Callback<T> responseHandler) {
    Response<T> response = server.getResource(new Request<>(resourceName, this));
    Optional.ofNullable(response.getResource()).ifPresent(responseHandler::call);
    return response.getResource();
  }

  /**
   * Send a resource to a server.
   * @param server The server to send the resource to.
   * @param resource The resource to be sent.
   */
  public T initiatePostRequest(Server<T> server, T resource) {
    Response<T> response = server.postResource(new Request<>(resource, this));
    return response.getResource();
  }

  /**
   * Delete a resource from a server.
   * @param server The server containing the resource.
   * @param resourceName The name of the resource to delete.
   */
  public T initiateDeleteRequest(Server<T> server, String resourceName) {
    Response<T> response = server.deleteResource(new Request<>(resourceName, this));
    return response.getResource();
  }

  /**
   * Getter for the name of this Client.
   * @return the name of the Client.
   */
  public String getClientName() {
    return clientName;
  }

  /**
   * Gettier for the ClientTier of this Client.
   * @return the ClientTier.
   */
  public ClientTier getTier() {
    return tier;
  }
}