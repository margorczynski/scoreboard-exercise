package io.github.margorczynski.scoreboard_exercise.scoreboard

import org.scalatest._
import flatspec._
import io.github.margorczynski.scoreboard_exercise.Match
import io.github.margorczynski.scoreboard_exercise.scoreboard.ScoreboardError.{MatchAlreadyExists, MatchNotFound, NegativeScoreError, TeamNameEmpty}
import matchers._

class InMemoryScoreboardSpec extends AnyFlatSpec with should.Matchers {

  private val homeTeamName = "home"
  private val awayTeamName = "away"

  //startNewGame tests
  "startNewGame" should "return a TeamNameEmpty error if a team name is empty" in {
    val testScoreboard = new InMemoryScoreboard

    val homeTeamEmptyResult = testScoreboard.startNewGame("", awayTeamName)
    val awayTeamEmptyResult = testScoreboard.startNewGame(homeTeamName, "")

    homeTeamEmptyResult shouldBe Left(TeamNameEmpty)
    awayTeamEmptyResult shouldBe Left(TeamNameEmpty)
  }

  "startNewGame" should "return a MatchAlreadyExists error if the given match already exists" in {
    val testScoreboard = new InMemoryScoreboard

    testScoreboard.startNewGame(homeTeamName, awayTeamName)

    val secondCreateResult = testScoreboard.startNewGame(homeTeamName, awayTeamName)

    secondCreateResult shouldBe Left(MatchAlreadyExists)
  }

  "startNewGame" should "succeed if the team names are not empty and the match doesn't already exist" in {
    val testScoreboard = new InMemoryScoreboard

    val startNewGameResult = testScoreboard.startNewGame(homeTeamName, awayTeamName)

    startNewGameResult shouldBe Right(())
  }

  //updateScore tests
  "updateScore" should "return a TeamNameEmpty error if a team name is empty" in {
    val testScoreboard = new InMemoryScoreboard

    val homeTeamEmptyResult = testScoreboard.updateScore("", awayTeamName, 1 ,1)
    val awayTeamEmptyResult = testScoreboard.updateScore(homeTeamName, "", 1, 1)

    homeTeamEmptyResult shouldBe Left(TeamNameEmpty)
    awayTeamEmptyResult shouldBe Left(TeamNameEmpty)
  }

  "updateScore" should "return a NegativeScoreError error if a team score is negative" in {
    val testScoreboard = new InMemoryScoreboard

    testScoreboard.startNewGame(homeTeamName, awayTeamName)

    val homeTeamNegativeScoreResult = testScoreboard.updateScore(homeTeamName, awayTeamName, -1, 1)
    val awayTeamNegativeScoreResult = testScoreboard.updateScore(homeTeamName, awayTeamName, 1, -1)

    homeTeamNegativeScoreResult shouldBe Left(NegativeScoreError)
    awayTeamNegativeScoreResult shouldBe Left(NegativeScoreError)
  }

  "updateScore" should "return a MatchNotFound error if the given match doesn't exist" in {
    val testScoreboard = new InMemoryScoreboard
    val nonExistentTeamName = "Non Existent"

    testScoreboard.startNewGame(homeTeamName, awayTeamName)

    val matchNotFoundResult = testScoreboard.updateScore(nonExistentTeamName, awayTeamName, 1 ,1)

    matchNotFoundResult shouldBe Left(MatchNotFound)
  }

  "updateScore" should "succeed if the match exists and the new scores are positive" in {
    val testScoreboard = new InMemoryScoreboard

    testScoreboard.startNewGame(homeTeamName, awayTeamName)

    val updateScoreResult = testScoreboard.updateScore(homeTeamName, awayTeamName, 1, 1)

    updateScoreResult shouldBe Right(())
  }

  //finishGame
  "finishGame" should "return a MatchNotFound error if the given match doesn't exist" in {
    val testScoreboard = new InMemoryScoreboard
    val nonExistentTeamName = "Non Existent"

    testScoreboard.startNewGame(homeTeamName, awayTeamName)

    val matchNotFoundResult = testScoreboard.finishGame(nonExistentTeamName, awayTeamName)

    matchNotFoundResult shouldBe Left(MatchNotFound)
  }

  "finishGame" should "succeed if the given match exists" in {
    val testScoreboard = new InMemoryScoreboard

    testScoreboard.startNewGame(homeTeamName, awayTeamName)

    val finishGameResult = testScoreboard.finishGame(homeTeamName, awayTeamName)

    finishGameResult shouldBe Right(())
  }

  //getGamesSummary
  "getGamesSummary" should "return an empty list if no match was started" in {
    val testScoreboard = new InMemoryScoreboard

    testScoreboard.getGamesSummary shouldBe Right(Nil)
  }

  "getGamesSummary" should "return the list of existing matches sorted by score sum and start timestamp (most recent first)" in {
    val testScoreboard = new InMemoryScoreboard

    val testMatches = Seq(
      Match("Mexico", "Canada", 0, 5),
      Match("Spain", "Brazil", 10, 2),
      Match("Germany", "France", 2, 2),
      Match("Uruguay", "Italy", 6, 6),
      Match("Argentina", "Australia", 3, 1)
    )

    testMatches.foreach { case Match(homeTeam, awayTeam, _, _) =>
      testScoreboard.startNewGame(homeTeam, awayTeam)
    }

    testScoreboard.getGamesSummary shouldBe Right(
      List(
        Match("Uruguay", "Italy", 6, 6),
        Match("Spain", "Brazil", 10, 2),
        Match("Mexico", "Canada", 0, 5),
        Match("Argentina", "Australia", 3, 1),
        Match("Germany", "France", 2, 2)
      )
    )
  }
}