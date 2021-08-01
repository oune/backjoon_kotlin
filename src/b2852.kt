import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()

    var score = 0
    var preTime = Pair(0, 0)
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

        if (score > 0) {

        } else if (score < 0) {

        }
    }
}