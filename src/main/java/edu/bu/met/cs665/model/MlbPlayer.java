/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: MlbPlayer.java
 * Description: This class is responsible for the methods and attributes of a MlbPlayer
 */

package edu.bu.met.cs665.model;

/**
 * This is the Player class. It is the conceptual representation
 * of a professional baseball player.
 */
public class MlbPlayer {
  private String name;
  private MlbTeam team;
  private Position position;
  private int jerseyNumber;
  private int atBats;
  private int hits;
  private int earnedRuns;
  private int inningsPitched;

  public MlbPlayer(
          String name,
          MlbTeam team,
          Position position,
          int jerseyNumber,
          int atBats,
          int hits,
          int earnedRuns,
          int inningsPitched
  ) {
    this.name = name;
    this.team = team;
    this.position = position;
    this.jerseyNumber = jerseyNumber;
    this.atBats = atBats;
    this.hits = hits;
    this.earnedRuns = earnedRuns;
    this.inningsPitched = inningsPitched;
  }

  public String getName() {
    return name;
  }

  public MlbTeam getTeam() {
    return team;
  }

  public Position getPosition() {
    return position;
  }

  public int getJerseyNumber() {
    return jerseyNumber;
  }

  public int getAtBats() {
    return atBats;
  }

  public int getHits() {
    return hits;
  }

  public int getEarnedRuns() {
    return earnedRuns;
  }

  public int getInningsPitched() {
    return inningsPitched;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
