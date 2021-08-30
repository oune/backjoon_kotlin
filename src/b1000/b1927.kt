package b1000

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val repeatCount = readLine()!!.toInt()
    val out = StringBuilder()
    val heap = PriorityQueue<Int>()

    repeat(repeatCount) {
        val input = readLine()!!.toInt()
        if (input == 0)
            if (heap.isEmpty())
                out.append("0\n")
            else
                out.append("${heap.poll()}\n")
        else
            heap.add(input)
    }

    print(out.toString())
}