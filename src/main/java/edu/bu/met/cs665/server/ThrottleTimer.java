/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: ThrottleTimer.java
 * Description: This class is responsible for the methods and attributes of a ThrottleTimer
 */

package edu.bu.met.cs665.server;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The ThrottleTimer class implements the Throttler interface
 * and periodically resets a RequestCounter which tracks a Clients
 * calls to a server of the defined time period.
 */
public class ThrottleTimer implements Throttler {
  private int throttlePeriod;
  private RequestCounter requestCounter;

  public ThrottleTimer(int throttlePeriod, RequestCounter requestCounter) {
    this.throttlePeriod = throttlePeriod;
    this.requestCounter = requestCounter;
  }

  @Override
  public void start() {
    new Timer(true).schedule(new TimerTask() {
      @Override
      public void run() {
        requestCounter.reset();
      }
    }, 0, throttlePeriod);
  }
}
