package io.github.margorczynski.scoreboard_exercise

import java.time.LocalDateTime

/**
 * Models a match of two given teams with a given score
 *
 * @param matchStartDateTime The LocalDateTime when the match started
 * @param homeTeam The name of the home team
 * @param awayTeam The name of the away team
 * @param homeTeamScore The score obtained by the home team
 * @param awayTeamScore The score obtained by the away team
 */
case class Match(matchStartDateTime: LocalDateTime, homeTeam: String, awayTeam: String, homeTeamScore: Int, awayTeamScore: Int)