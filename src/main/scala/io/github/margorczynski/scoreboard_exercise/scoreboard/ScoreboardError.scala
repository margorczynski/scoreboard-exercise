package io.github.margorczynski.scoreboard_exercise.scoreboard

sealed trait ScoreboardError
object ScoreboardError {
  type ScoreboardResult[T] = Either[ScoreboardError, T]
}