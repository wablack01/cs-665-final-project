/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: Request.java
 * Description: This class is responsible for the methods and attributes of a request.
 */

package edu.bu.met.cs665.client;

/**
 * The request class represents a request issued from a Client to a resource Server
 * @param <T> the resource type handled by the Client and Server
 */
public class Request<T> {
  private T resource;
  private String resourceName;
  private Client<T> client;

  /**
   * Instantiate a Request for the given resource name
   * @param resourceName the name of the resource being requested
   * @param client the Client sending the request
   */
  public Request(String resourceName, Client<T> client) {
    this.resourceName = resourceName;
    this.client = client;
  }

  /**
   * Instantiate a Request for the given resource
   * @param resource the resource being requested
   * @param client the Client sending the request
   */
  public Request(T resource, Client client) {
    this.resource = resource;
    this.client = client;
  };

  /**
   * Getter for the resource held in this request.
   * @return the resource held in this request.
   */
  public T getResource() {
    return this.resource;
  }

  /**
   * Getter for the resource name held in this request.
   * @return the resource name held in this request.
   */
  public String getResourceName() {
    return this.resourceName;
  }

  /**
   * Getter for the Client that sent this request.
   * @return the Client that sent this request.
   */
  public Client<T> getClient() {
    return this.client;
  }
}
