/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: RequestCounter.java
 * Description: This class is responsible for the methods and attributes of a RequestCounter.
 */

package edu.bu.met.cs665.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The RequestCounter class tracks the amount of Request objects
 * sent from a Client to a Server.
 */
public class RequestCounter {
  private final Map<String, AtomicLong> requestCounts = new ConcurrentHashMap<>();

  /**
   * Add a Client to the counter.
   * @param clientName the name of the Client.
   */
  public void addClient(String clientName) {
    requestCounts.putIfAbsent(clientName, new AtomicLong(0));
  }

  /**
   * Increment of the request count for a Client.
   * @param clientName the name of the Client to increment.
   */
  public void incrementCount(String clientName) {
    requestCounts.get(clientName).incrementAndGet();
  }

  /**
   * Get the request count for a Client.
   * @param clientName the name of the Client.
   * @return the number of requests sent by the Client.
   */
  public long getCount(String clientName) {
    return requestCounts.get(clientName).get();
  }

  /**
   * Reset all Client request counts to 0
   */
  public void reset() {
    requestCounts.replaceAll((k,v) -> new AtomicLong(0));
    System.out.println("Client requests reset");
  }
}
