package edu.bu.met.cs665;

import edu.bu.met.cs665.callbacks.MlbPlayerCallbacks;
import edu.bu.met.cs665.client.Client;
import edu.bu.met.cs665.client.ClientTier;
import edu.bu.met.cs665.model.MlbPlayer;
import edu.bu.met.cs665.model.MlbPlayerBuilder;
import edu.bu.met.cs665.model.MlbTeam;
import edu.bu.met.cs665.model.Position;
import edu.bu.met.cs665.server.RequestCounter;
import edu.bu.met.cs665.server.Server;
import edu.bu.met.cs665.server.ThrottleTimer;
import edu.bu.met.cs665.server.Throttler;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Write some Unit tests for your program like the examples below.
 */

public class TestMlbPlayer {

  public TestMlbPlayer(){};

  @Test
  public void testPlayerBuilder() {
    MlbPlayer player = new MlbPlayerBuilder("Logan Webb")
            .setTeam(MlbTeam.SFG)
            .setPosition(Position.PITCHER)
            .setJerseyNumber(62)
            .setAtBats(0)
            .setHits(0)
            .setEarnedRuns(14)
            .setInningsPitched(42)
            .build();

    assertEquals("Logan Webb", player.getName());
    assertEquals(MlbTeam.SFG, player.getTeam());
    assertEquals(Position.PITCHER, player.getPosition());
    assertEquals(62, player.getJerseyNumber());
    assertEquals(0, player.getAtBats());
    assertEquals(0, player.getHits());
    assertEquals(14, player.getEarnedRuns());
    assertEquals(42, player.getInningsPitched());
  }

  @Test
  public void testGetResource() {
    MlbPlayer player = new MlbPlayerBuilder("Logan Webb")
            .build();

    MlbPlayer player2 = new MlbPlayerBuilder("Mike Trout")
            .build();

    RequestCounter requestCounter = new RequestCounter();
    Throttler timer = new ThrottleTimer(1000, requestCounter);
    Server<MlbPlayer> server = new Server<>(requestCounter, timer, 5);
    Client<MlbPlayer> adminClient = new Client<>("Admin Client", ClientTier.ADMIN, requestCounter);
    Client<MlbPlayer> standardClient = new Client<>("Standard Client", ClientTier.STANDARD, requestCounter);

    adminClient.initiatePostRequest(server, player);
    MlbPlayer getPlayer = standardClient.initiateGetRequest(server, "Logan Webb", MlbPlayerCallbacks.calcEarnedRunAvg);

    standardClient.initiatePostRequest(server, player2);
    MlbPlayer getPlayer2 = standardClient.initiateGetRequest(server, "Mike Trout", MlbPlayerCallbacks.calcEarnedRunAvg);

    assertEquals(player, getPlayer);
    assertNull(getPlayer2);
  }

  @Test
  public void testPostResource() {
    MlbPlayer player = new MlbPlayerBuilder("Logan Webb")
            .build();

    RequestCounter requestCounter = new RequestCounter();
    Throttler timer = new ThrottleTimer(1000, requestCounter);
    Server<MlbPlayer> server = new Server<>(requestCounter, timer, 5);
    Client<MlbPlayer> standardClient = new Client<>("Standard Client", ClientTier.STANDARD, requestCounter);

    standardClient.initiatePostRequest(server, player);
    MlbPlayer getPlayer = standardClient.initiateGetRequest(server, "Logan Webb", MlbPlayerCallbacks.calcEarnedRunAvg);

    assertNull(getPlayer);
  }

  @Test
  public void testCallback() {

  }

  @Test
  public void testThrottler() {

  }
}
