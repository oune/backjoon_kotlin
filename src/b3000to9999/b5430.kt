package b3000to9999

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

fun main() = with(System.`in`.bufferedReader()) {
    val case = readLine().toInt()
    val sout = BufferedWriter(OutputStreamWriter(System.out))

    repeat(case) {
        val functions = readLine()
        val count = readLine().toInt()
        val input = readLine()

        val deque = ArrayDeque<Int>()
        if (input != "[]")
            input.substring(1, input.lastIndex).split(",").map { it.toInt() }.forEach {
                deque.add(it)
            }

        var isReversed = false

        if (deque.size != count) {
            sout.appendLine("error")
        } else {
            var isError = false

            functions.forEach {
                when(it) {
                    'R' -> isReversed = !isReversed;
                    'D' ->  {
                        if (deque.isEmpty()) {
                            isError = true
                            return@forEach
                        }

                        if (isReversed)
                            deque.removeLast()
                        else
                            deque.removeFirst()
                    }
                    else -> Unit
                }
            }

            if(isError)
                sout.appendLine("error")
            else {
                sout.append("[")
                if (deque.isNotEmpty()) {
                    if (isReversed) {
                        for (i in deque.lastIndex downTo 1) {
                            sout.append("${deque[i]},")
                        }
                        sout.append(deque[0].toString())
                    } else {
                        sout.append(deque[0].toString())

                        for (i in 1 until deque.size) {
                            sout.append(",${deque[i]}")
                        }
                    }
                }
                sout.appendLine("]")
            }
        }
    }

    sout.flush()
    sout.close()
}