package io.github.margorczynski.scoreboard_exercise.scoreboard

import io.github.margorczynski.scoreboard_exercise.MatchScore
import io.github.margorczynski.scoreboard_exercise.scoreboard.ScoreboardError.ScoreboardResult


trait Scoreboard {
  def startNewGame(homeTeam: String, awayTeam: String): ScoreboardResult[Unit]
  def updateScore(homeTeam: String, awayTeam: String, newHomeTeamScore: Int, newAwayTeamScore: Int): ScoreboardResult[Unit]
  def finishGame(homeTeam: String, awayTeam: String): ScoreboardResult[Unit]
  def getGamesSummary: ScoreboardResult[List[MatchScore]]
}
