package io.github.margorczynski.scoreboard_exercise.scoreboard

import org.scalatest._
import flatspec._
import matchers._

class InMemoryScoreboardSpec extends AnyFlatSpec with should.Matchers {

  //startNewGame tests
  "startNewGame" should "return a TeamNameEmpty error if a team name is empty" in {

  }

  "startNewGame" should "return a NegativeScoreError error if a team score is negative" in {

  }

  "startNewGame" should "return a MatchAlreadyExists error if the given match already exists" in {

  }

  "startNewGame" should "succeed if the team names are not empty and the scores positive" in {

  }

  //updateScore tests
  "updateScore" should "return a TeamNameEmpty error if a team name is empty" in {

  }

  "updateScore" should "return a NegativeScoreError error if a team score is negative" in {

  }

  "updateScore" should "return a MatchNotFound error if the given match doesn't exist" in {

  }

  "updateScore" should "succeed if the match exists and the new scores are positive" in {

  }

  //finishGame
  "finishGame" should "return a MatchNotFound error if the given match doesn't exist" in {

  }

  "finishGame" should "succeed if the given match exists" in {

  }

  //getGamesSummary
  "getGamesSummary" should "return the list of existing matches sorted by score sum and start timestamp" in {

  }
}