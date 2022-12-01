package io.github.margorczynski.scoreboard_exercise.scoreboard

/**
 * The below will model all the possible invalid results of operating on the Scoreboard
 */
sealed trait ScoreboardError extends Product with Serializable
object ScoreboardError {
  type ScoreboardResult[T] = Either[ScoreboardError, T]

  case object NegativeScoreError extends ScoreboardError
  case object TeamNameBlank extends ScoreboardError
  case class MatchAlreadyExists(homeTeamName: String, awayTeamName: String) extends ScoreboardError
  case class MatchNotFound(homeTeamName: String, awayTeamName: String) extends ScoreboardError
}