import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.ceil

fun main() = with (BufferedReader(InputStreamReader(System.`in`))) {
    val (x, y) = readLine().split(" ").map{ it.toInt() }
    val z = y * 100 / x.toDouble()
    val res = when (x) {
        y -> -1
        else -> {
            val increment = (y + 1) * 100 / (x + 1).toDouble() - z
            println("increment: $increment")
            println("res: ${1 / increment}")
            if (increment >= 1) 1 else ceil(1 / increment).toInt()
        }
    }

    print(res)
}