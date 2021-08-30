package b1000

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine()
    val commandCount = readLine().toInt()

    val left = Stack<Char>()
    input.forEach {
        left.push(it)
    }
    val right = Stack<Char>()

    repeat(commandCount) {
        val command = readLine().split(" ")
        when (command[0]) {
            "L" -> {
                if (left.isNotEmpty())
                    right.push(left.pop())
            }
            "D" -> {
                if (right.isNotEmpty())
                    left.push(right.pop())
            }
            "B" -> {
                if (left.isNotEmpty())
                    left.pop()
            }
            "P" -> {
                val char = command[1].toCharArray()[0]
                left.push(char)
            }
            else -> {}
        }
    }

    val out = BufferedWriter(OutputStreamWriter(System.out))
    left.forEach{
        out.append(it)
    }
    right.reversed().forEach {
        out.append(it)
    }
    out.flush()
    out.close()
}