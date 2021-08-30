import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()

    var score = 0
    var preTime = Pair(0, 0)
    var preScore = 0
    var team1 = Pair(0, 0)
    var team2 = Pair(0, 0)

    repeat(n) {
        val (team, time) = readLine().split(" ")
        val (minute, second) = time.split(":").map { it.toInt() }

        if (team == "1") {
            score++
        } else {
            score--
        }

        if (preScore == 0) {
            preTime = Pair(minute, second)
        }

        if (score == 0) {
            if (preScore > 0) {
                team1 = timeAdd(team1, preTime, minute, second)
            } else {
                team2 = timeAdd(team2, preTime, minute, second)
            }
        }

        preScore = score
    }
    if (score > 0) {
        team1 = resTimeAdd(team1, preTime)
    } else if (score < 0){
        team2 = resTimeAdd(team2, preTime)
    }

    team1 = timeCheck(team1)
    team2 = timeCheck(team2)


    println(format(team1))
    println(format(team2))
}

fun timeAdd(team: Pair<Int, Int>, preTime: Pair<Int, Int>, minute: Int, second: Int) =  Pair(team.first + minute - preTime.first, team.second + second - preTime.second)
fun resTimeAdd(team: Pair<Int, Int>, preTime: Pair<Int, Int>) = Pair(team.first + 48 - preTime.first, team.second - preTime.second)
fun timeCheck(team: Pair<Int, Int>) :Pair<Int, Int> {
    var teamTime = team
    while (teamTime.second < 0) {
        teamTime = Pair(teamTime.first - 1, teamTime.second + 60)
    }
    while (teamTime.second > 59) {
        teamTime = Pair(teamTime.first + 1, teamTime.second - 60)
    }
    return teamTime
}

fun format10 (num: Int): String {
    if (num < 10) {
        return "0$num"
    } else {
        return num.toString()
    }
}

fun format(time: Pair<Int, Int>): String {
    return "${format10(time.first)}:${format10(time.second)}"
}