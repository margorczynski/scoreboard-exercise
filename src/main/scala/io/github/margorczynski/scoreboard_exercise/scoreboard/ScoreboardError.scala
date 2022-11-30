package io.github.margorczynski.scoreboard_exercise.scoreboard

/**
 * The below will model all the possible invalid results of operating on the Scoreboard
 */
sealed trait ScoreboardError
object ScoreboardError {
  type ScoreboardResult[T] = Either[ScoreboardError, T]

  case object NegativeScoreError extends ScoreboardError
  case object TeamNameEmpty extends ScoreboardError
  case class MatchAlreadyExists(homeTeamName: String, awayTeamName: String)
  case class MatchNotFound(teamName: String) extends ScoreboardError
}