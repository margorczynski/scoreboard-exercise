package io.github.margorczynski.scoreboard_exercise.scoreboard

import io.github.margorczynski.scoreboard_exercise.Match
import io.github.margorczynski.scoreboard_exercise.scoreboard.ScoreboardError.ScoreboardResult


/**
 * An interface for a scoreboard containing the basic operations that should be accessible.
 * Because of the requirement placed on getGamesSummary the underlying storage should track the order and/or creation time of the matches
 */
trait Scoreboard {
  /**
   * Create a match. The initial score for both team is 0.
   * Fail if an empty team name or the given match already exists.
   *
   * @param homeTeam Home team name
   * @param awayTeam Away team name
   * @return Either an error or an Unit empty result on success
   */
  def startNewGame(homeTeam: String, awayTeam: String): ScoreboardResult[Unit]

  /**
   * Update the score of a given match.
   * ail if the given match doesn't exist, an empty team name was passed or any of the new scores is negative
   *
   * @param homeTeam Home team name
   * @param awayTeam Away team name
   * @param newHomeTeamScore Home team new score
   * @param newAwayTeamScore Away team new score
   * @return Either an error or an Unit empty result on success
   */
  def updateScore(homeTeam: String, awayTeam: String, newHomeTeamScore: Int, newAwayTeamScore: Int): ScoreboardResult[Unit]

  /**
   * Finish a match removing it from the scoreboard.
   * Fail if the given match doesn't exist or an empty team name was passed
   *
   * @param homeTeam Home team name
   * @param awayTeam Away team name
   * @return Either an error or an Unit empty result on success
   */
  def finishGame(homeTeam: String, awayTeam: String): ScoreboardResult[Unit]

  /**
   * Return a list of all of the matches ordered by the score sum (descending) and start order (earlier first)
   *
   * @return Either an error or the list of matches
   */
  def getGamesSummary: ScoreboardResult[List[Match]]
}
