package io.github.margorczynski.scoreboard_exercise.scoreboard

import io.github.margorczynski.scoreboard_exercise.Match
import io.github.margorczynski.scoreboard_exercise.scoreboard.ScoreboardError.ScoreboardResult

class InMemoryScoreboard extends Scoreboard {
  def startNewGame(homeTeam: String, awayTeam: String): ScoreboardResult[Unit] = ???

  def updateScore(homeTeam: String, awayTeam: String, newHomeTeamScore: Int, newAwayTeamScore: Int): ScoreboardResult[Unit] = ???

  def finishGame(homeTeam: String, awayTeam: String): ScoreboardResult[Unit] = ???

  def getGamesSummary: ScoreboardResult[List[Match]] = ???
}