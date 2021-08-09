import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.pow
import kotlin.math.sqrt

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val sout = BufferedWriter(OutputStreamWriter(System.out))
    val t = readLine().toInt()
    val isInside = { x: Int, y: Int, cx: Int, cy: Int, r: Int ->
        val res = sqrt((x - cx).toDouble().pow(2) + (y - cy).toDouble().pow(2))
        res < r
    }

    repeat(t) {
        val (x1, y1, x2, y2) = readLine().split(" ").map { it.toInt() }
        val starCount = readLine().toInt()
        var count = 0
        repeat(starCount) {
            val (cx, cy, r) = readLine().split(" ").map { it.toInt() }
            val isStartIn = isInside(x1, y1, cx, cy, r)
            val isDestinationIn = isInside(x2, y2, cx, cy, r)

            if (!isStartIn || !isDestinationIn) {
                if (isStartIn) {
                    count++
                } else if (isDestinationIn) {
                    count++
                }
            }
        }
        sout.appendLine(count.toString())
    }
    sout.flush()
    sout.close()
}