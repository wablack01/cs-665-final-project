/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: Main.java
 * Description: Write a description for this class
 */

package edu.bu.met.cs665;

import edu.bu.met.cs665.callbacks.Callback;
import edu.bu.met.cs665.callbacks.MlbPlayerCallbacks;
import edu.bu.met.cs665.client.Client;
import edu.bu.met.cs665.client.ClientTier;
import edu.bu.met.cs665.model.MlbTeam;
import edu.bu.met.cs665.model.MlbPlayer;
import edu.bu.met.cs665.model.MlbPlayerBuilder;
import edu.bu.met.cs665.model.Position;
import edu.bu.met.cs665.server.RequestCounter;
import edu.bu.met.cs665.server.Server;
import edu.bu.met.cs665.server.ThrottleTimer;
import edu.bu.met.cs665.server.Throttler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * This is the Main class.
 */
public class Main {

  private static final int STANDARD_RATE_LIMIT = 5;
  private static final int THROTTLE_PERIOD = 1000;
  private static final int TIMEOUT = 10;

  /**
   * A main method to run examples.
   */
  public static void main(String[] args) {

    RequestCounter requestCounter = new RequestCounter();
    Throttler timer = new ThrottleTimer(THROTTLE_PERIOD, requestCounter);
    Server<MlbPlayer> server = new Server<>(requestCounter, timer, STANDARD_RATE_LIMIT);

    MlbPlayer mikeTrout = new MlbPlayerBuilder("Mike Trout")
            .setTeam(MlbTeam.LAA)
            .setPosition(Position.FIELDER)
            .setJerseyNumber(27)
            .setAtBats(109)
            .setHits(24)
            .setEarnedRuns(0)
            .setInningsPitched(0)
            .build();

    MlbPlayer loganWebb = new MlbPlayerBuilder("Logan Webb")
            .setTeam(MlbTeam.SFG)
            .setPosition(Position.PITCHER)
            .setJerseyNumber(62)
            .setAtBats(0)
            .setHits(0)
            .setEarnedRuns(14)
            .setInningsPitched(42)
            .build();

    Client<MlbPlayer> adminClient = new Client<>("Admin Client", ClientTier.ADMIN, requestCounter);
    Client<MlbPlayer> standardClient = new Client<>("Standard Client", ClientTier.STANDARD, requestCounter);
    Client<MlbPlayer> premiumClient = new Client<>("Premium Client", ClientTier.PREMIUM, requestCounter);

    adminClient.initiatePostRequest(server, mikeTrout);
    adminClient.initiatePostRequest(server, loganWebb);

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(() -> initServerCalls(standardClient, server,
            mikeTrout.getName(), MlbPlayerCallbacks.calcBattingAvg));
    executorService.execute(() -> initServerCalls(premiumClient, server,
            loganWebb.getName(), MlbPlayerCallbacks.calcEarnedRunAvg));
    executorService.shutdown();

    try {
      executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.err.printf("Executor service terminated: %s", e.getMessage());
    }

    adminClient.initiateDeleteRequest(server, mikeTrout.getName());
    adminClient.initiateDeleteRequest(server, loganWebb.getName());
  }

  private static void initServerCalls(Client<MlbPlayer> client, Server server, String resourceName, Callback callback) {

    IntStream.range(0, 50).forEach(i -> {
      MlbPlayer p = client.initiateGetRequest(server, resourceName, callback);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        System.err.printf("Thread interrupted: %s", e.getMessage());
      }
    });
  }
}
