import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (nCount, m) = readLine().split(" ").map { it.toInt()}
    val n = readLine().split(" ").map { it.toInt() }

    val sOut = StringBuilder()
    repeat(m) {
        val (i, j) = readLine().split(" ").map { it.toInt() }
        sOut.appendLine(sum(i - 1, j - 1, n))
    }

    print(sOut.toString())
}

fun sum(i: Int, j: Int, list: List<Int>) = sum (i, j, list, 0)

tailrec fun sum(i: Int, j: Int, list: List<Int>, acc:Int) :Int = when {
    i > j -> acc
    else -> {
        sum(i + 1, j, list, acc + list[i])
    }
}