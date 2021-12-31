package b10000

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

data class point(val x:Int, val y:Int)

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val arr = Array(size) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    for (k in 0 until size) {
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (arr[i][k] == 1 && arr[k][j] == 1)
                    arr[i][j] = 1
            }
        }
    }

    BufferedWriter(OutputStreamWriter(System.out)).use { bw ->
        arr.forEach { intArr ->
            intArr.forEach {
                bw.append("$it ")
            }
            bw.newLine()
        }
        bw.flush()
    }
}