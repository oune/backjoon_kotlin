import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (nCount, m) = readLine().split(" ").map { it.toInt()}
    val n = readLine().split(" ").map { it.toInt() }
    val acc = accSum(n, listOf())
    val sOut = BufferedWriter(OutputStreamWriter(System.out))

    repeat(m) {
        val (i, j) = readLine().split(" ").map { it.toInt() }
        if (i == 1) {
            sOut.appendLine(acc[j - 1].toString())
        } else {
            sOut.appendLine((acc[j - 1] - acc[i - 2]).toString())
        }
    }

    sOut.flush()
    sOut.close()
}

tailrec fun accSum(list: List<Int>, acc:List<Int>) :List<Int> = when {
    list.isEmpty() -> acc
    acc.isEmpty() -> accSum(list.drop(1), listOf(list.first()))
    else -> {
        accSum(list.drop(1),acc + listOf(list.first() + acc.last()))
    }
}