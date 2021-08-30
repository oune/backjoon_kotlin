package b3000to9999

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val testCase = readLine()!!.toInt()
    val out = BufferedWriter(OutputStreamWriter(System.`out`))

    repeat(testCase) {
        val calCount = readLine()!!.toInt()
        val map = TreeMap<Int, Int>()

        repeat(calCount) {
            val line = readLine()!!.split(" ")
            val operator = line[0]
            val operand = line[1].toInt()

            when {
                operator == "I" -> {
                    map[operand] = map.getOrDefault(operand, 0) + 1
                }
                map.isNotEmpty() && operator == "D" -> {
                    val num = when (operand) {
                        1 -> map.lastKey()
                        -1 -> map.firstKey()
                        else -> null
                    }

                    map[num!!] = map[num]!! - 1
                    if (map[num] == 0)
                        map.remove(num)
                }
            }
        }
        if (map.isNotEmpty())
            out.append("${map.lastKey()} ${map.firstKey()}\n")
        else
            out.append("EMPTY\n")
    }

    out.flush()
    out.close()
}