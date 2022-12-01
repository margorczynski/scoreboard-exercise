package io.github.margorczynski.scoreboard_exercise.scoreboard

import io.github.margorczynski.scoreboard_exercise.Match
import io.github.margorczynski.scoreboard_exercise.scoreboard.ScoreboardError._

import java.time.LocalDateTime
import scala.collection.concurrent.TrieMap

class InMemoryScoreboard extends Scoreboard {

  //Pair Home Team Name - Away Team Name
  type MatchIdentifier = (String, String)
  //Tuple of Home Team Score - Away Team Score and the creation time, bit of a hack to encode order of creation with a hash map
  type MatchScoreWithCreationDateTime = (Int, Int, LocalDateTime)

  def startNewGame(homeTeam: String, awayTeam: String): ScoreboardResult[Unit] = for {
    _ <- blankTeamNameCheck(homeTeam)
    _ <- blankTeamNameCheck(awayTeam)
    _ <- matchAlreadyExistsCheck(homeTeam, awayTeam)
  } yield storage.update(homeTeam -> awayTeam, (0, 0, LocalDateTime.now()))


  def updateScore(homeTeam: String, awayTeam: String, newHomeTeamScore: Int, newAwayTeamScore: Int): ScoreboardResult[Unit] = for {
    _ <- blankTeamNameCheck(homeTeam)
    _ <- blankTeamNameCheck(awayTeam)
    _ <- scoreIsNegativeCheck(newHomeTeamScore)
    _ <- scoreIsNegativeCheck(newAwayTeamScore)
    oldMatch <- storage.get(homeTeam -> awayTeam).toRight(MatchNotFound(homeTeam, awayTeam))
  } yield storage.update(homeTeam -> awayTeam, (newHomeTeamScore, newAwayTeamScore, oldMatch._3))

  def finishGame(homeTeam: String, awayTeam: String): ScoreboardResult[Unit] = for {
    _ <- blankTeamNameCheck(homeTeam)
    _ <- blankTeamNameCheck(awayTeam)
    _ <- storage.remove(homeTeam -> awayTeam).toRight(MatchNotFound(homeTeam, awayTeam))
  } yield ()

  def getGamesSummary: ScoreboardResult[List[Match]] = Right(
    storage
      .toList
      .sortBy { case (_, (homeTeamScore, awayTeamScore, startDateTime)) =>
        (homeTeamScore + awayTeamScore, startDateTime)
      }
      .reverse
      .map { case ((homeTeamName, awayTeamName), (homeTeamScore, awayTeamScore, _)) =>
        Match(homeTeamName, awayTeamName, homeTeamScore, awayTeamScore)
      }
  )


  //Thread safe hashmap, O(1) lookup and update benefits greatly updating the scores
  private val storage = TrieMap[MatchIdentifier, MatchScoreWithCreationDateTime]()

  private def blankTeamNameCheck(teamName: String) =
    Either.cond[ScoreboardError, Unit](!teamName.isBlank, (), TeamNameBlank)

  private def matchAlreadyExistsCheck(homeTeamName: String, awayTeamName: String) =
    Either.cond[ScoreboardError, Unit](!storage.contains(homeTeamName -> awayTeamName), (), MatchAlreadyExists(homeTeamName, awayTeamName))

  private def scoreIsNegativeCheck(score: Int) =
    Either.cond(score >= 0, (), NegativeScoreError)
}