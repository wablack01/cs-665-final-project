/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: Throttler.java
 * Description: This interface defines the method signatures of a Throttler
 */

package edu.bu.met.cs665.server;

/**
 * The Throttler interface is used to limit a Clients
 * use of a Server over a period of time.
 */
public interface Throttler {
  void start();
}
