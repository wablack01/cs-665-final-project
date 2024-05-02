/**
 * Name: Walker Black
 * Course: CS-665 Software Designs & Patterns
 * Date: 05/02/2024
 * File Name: MlbPlayerBuilder.java
 * Description: This class is responsible for the methods and attributes of a MlbPlayerBuilder.
 */

package edu.bu.met.cs665.model;

/**
 * The MlbPlayerBuilder class provides methods to simplify
 * the construction of MlbPlayer objects.
 */
public class MlbPlayerBuilder {
  private String name;
  private MlbTeam team = null;
  private Position position = null;
  private int jerseyNumber = 0;
  private int atBats = 0;
  private int hits = 0;
  private int earnedRuns = 0;
  private int inningsPitched = 0;

  /**
   * Instantiate a new PlayerBuilder with the name of the
   * Player to be constructed. This is the only required
   * parameter for a Player.
   * @param name the name of the Player to be constructed.
   */
  public MlbPlayerBuilder(String name) {
    this.name = name;
  }

  public MlbPlayerBuilder setTeam(MlbTeam team) {
    this.team = team;
    return this;
  }

  public MlbPlayerBuilder setPosition(Position position) {
    this.position = position;
    return this;
  }

  public MlbPlayerBuilder setJerseyNumber(int jerseyNumber) {
    this.jerseyNumber = jerseyNumber;
    return this;
  }

  public MlbPlayerBuilder setAtBats(int atBats) {
    this.atBats = atBats;
    return this;
  }

  public MlbPlayerBuilder setHits(int hits) {
    this.hits = hits;
    return this;
  }

  public MlbPlayerBuilder setEarnedRuns(int earnedRuns) {
    this.earnedRuns = earnedRuns;
    return this;
  }

  public MlbPlayerBuilder setInningsPitched(int inningsPitched) {
    this.inningsPitched = inningsPitched;
    return this;
  }

  /**
   * Construct a new Player with the values set in the PlayerBuilder
   * @return the constructed Player.
   */
  public MlbPlayer build() {
    MlbPlayer mlbPlayer = new MlbPlayer(
            this.name,
            this.team,
            this.position,
            this.jerseyNumber,
            this.atBats,
            this.hits,
            this.earnedRuns,
            this.inningsPitched
    );

    return mlbPlayer;
  }
}
