import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.roundToInt

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val count = readLine().toInt()
    var sum = 0.0
    var totalTime = 0
    repeat(count) {
        val inputs = readLine().split(" ").drop(1)
        val score = when (inputs[1]) {
            "A+" -> 4.3
            "A0" -> 4.0
            "A-" -> 3.7
            "B+" -> 3.3
            "B0" -> 3.0
            "B-" -> 2.7
            "C+" -> 2.3
            "C0" -> 2.0
            "C-" -> 1.7
            "D+" -> 1.3
            "D0" -> 1.0
            "D-" -> 0.7
            "F" -> 0.0
            else -> 0
        }
        val time = inputs[0].toInt()
        totalTime += time
        sum += score.toDouble() * time.toDouble()
    }

    val res = (sum / totalTime * 100).roundToInt() / 100.0
    val out = BufferedWriter(OutputStreamWriter(System.out))

    out.append(String.format("%.2f", res))
    out.flush()
    out.close()
}