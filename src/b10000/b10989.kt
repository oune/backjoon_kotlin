package b10000

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val out = BufferedWriter(OutputStreamWriter(System.out))
    val lineCount = readLine().toInt()

    val arr = Array(10001) { 0 }

    repeat(lineCount) {
        val now = readLine().toInt()
        arr[now]++
    }

    arr.forEachIndexed { index, i ->
       repeat(i) {
           out.appendLine(index.toString())
       }
    }

    out.close()
}