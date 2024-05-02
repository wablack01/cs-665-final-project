/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: Response.java
 * Description: This class is responsible for the methods and attributes of a Response.
 */

package edu.bu.met.cs665.server;

/**
 * The Response class represents a response sent from a resource Server to a Client.
 * @param <T> the type of resources handled by the Server and Client.
 */
public class Response<T> {
  public static final int SUCCESS = 0;
  public static final int FAIL = 1;

  private final T resource;
  private final int status;
  private final String message;

  /**
   * Instantiate a response with the given parameters.
   * @param resource the resource being returned.
   * @param status the status of the response.
   * @param message a message from the server.
   */
  public Response(T resource, int status, String message) {
    this.resource = resource;
    this.status = status;
    this.message = message;
  }

  /**
   * Getter for the resource in the Response.
   * @return the resource in the Response.
   */
  public T getResource() {
    return resource;
  }

  /**
   * Getter for the status of the Response.
   * @return the status of the Response.
   */
  public int getStatus() {
    return status;
  }

  /**
   * Getter for the message in the Response.
   * @return the message in the Response.
   */
  public String getMessage() {
    return message;
  }
}
