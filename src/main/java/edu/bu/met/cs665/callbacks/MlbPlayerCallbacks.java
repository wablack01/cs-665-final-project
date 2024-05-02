/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: MlbPlayerCallbacks.java
 * Description: This is a library of static lambda functions operating on MlbPlayer object types
 */

package edu.bu.met.cs665.callbacks;

import edu.bu.met.cs665.model.MlbPlayer;

/**
 * This is the MlbPlayerCallbacks class. It contains Callback objects
 * implemented as lambda functions to be used elsewhere in the application.
 */
public class MlbPlayerCallbacks {
  private MlbPlayerCallbacks() {};

  /**
   * Calculate a player's earned run average
   */
  public static Callback<MlbPlayer> calcEarnedRunAvg = (MlbPlayer mlbPlayer) -> {
    int INNINGS_PER_GAME = 9;
    double era = ((double) mlbPlayer.getEarnedRuns() / (double) mlbPlayer.getInningsPitched()) * INNINGS_PER_GAME;
    System.out.printf("--- %s's earned run average: %,.3f\n", mlbPlayer.getName(), era);
  };

  /**
   * Calculate a player's batting average
   */
  public static Callback<MlbPlayer> calcBattingAvg = (MlbPlayer mlbPlayer) -> {
    double battingAverage = (double) mlbPlayer.getHits() / (double) mlbPlayer.getAtBats();
    System.out.printf("--- %s's batting average: %,.3f\n", mlbPlayer.getName(), battingAverage);
  };
}
