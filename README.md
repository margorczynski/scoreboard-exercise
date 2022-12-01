## Scoreboard Exercise

### Purpose
A basic coding exercise to showcase creating a elementary (unit tested)
library in the Scala language

### How to execute test suite

#### SBT
The project is built using SBT. To download and install it please visit:
https://www.scala-sbt.org/download.html

#### Running the tests
From the project directory please execute the command `sbt test`

In general the project code can be compiled using `sbt compile`

### Assumptions
- We assume no use of null values for names
- There is a finite amount of matches
- A team name is of finite length
- A team name is a non-blank string
- Scores which are less than Int.Min won't be passed as arguments 
- A match score cannot be bigger than Int.Max
- Creating a match with already existing home-away team names causes an error, 
 not an overwrite
- We assume that the underlying Scoreboard implementation encodes order of insertion