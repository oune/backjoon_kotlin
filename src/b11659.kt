import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (nCount, m) = readLine().split(" ").map { it.toInt()}

    var sum = 0
    val n = readLine().split(" ").map {
        sum += it.toInt()
        sum
    }

    val sOut = BufferedWriter(OutputStreamWriter(System.out))

    repeat(m) {
        val (i, j) = readLine().split(" ").map { it.toInt() }
        if (i == 1) {
            sOut.appendLine(n[j - 1].toString())
        } else {
            sOut.appendLine((n[j - 1] - n[i - 2]).toString())
        }
    }

    sOut.flush()
    sOut.close()
}