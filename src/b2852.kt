import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()

    var score = 0
    var preTime = Pair(0, 0)
    var preScore = 0;
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
                team1 = Pair(team1.first + minute - preTime.first, team1.second + second - preTime.second)
            } else {
                team2 = Pair(team2.first + minute - preTime.first, team2.second + second - preTime.second)
            }
        }

        preScore = score
    }
    if (score > 0) {
        team1 = Pair(team1.first + 48 - preTime.first, team1.second - preTime.second)
    } else if (score < 0){
        team2 = Pair(team2.first + 48 - preTime.first, team2.second - preTime.second)
    }

    while (team1.second < 0) {
        team1 = Pair(team1.first - 1, team1.second + 60)
    }

    while (team2.second < 0) {
        team2 = Pair(team2.first - 1, team2.second + 60)
    }

    val format = { num: Int ->
        if (num < 10) {
            "0$num"
        } else {
            num.toString()
        }
    }
    println("${format(team1.first)}:${format(team1.second)}")
    println("${format(team2.first)}:${format(team2.second)}")
}