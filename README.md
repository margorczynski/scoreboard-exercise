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

### Assumptions
- There is a finite amount of matches
- A team name is of finite length
- Scores which are less than Int.Min won't be passed as arguments 
- A match score cannot be bigger than Int.Max
- Creating a match with already existing home-away team names causes an error, 
 not an overwrite
- We will use the local date time of the system on which the project and tests
are executed