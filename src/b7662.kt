import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val testCase = readLine()!!.toInt()
    val out = BufferedWriter(OutputStreamWriter(System.out))

    repeat(testCase) {
        val calCount = readLine()!!.toInt()
        val map = TreeMap<Int, Int>()

        repeat(calCount) {
            val line = readLine()!!.split(" ")
            val operator = line[0]
            val operand = line[1].toInt()

            when (operator){
                "I" -> {
                    map[operand] = map.getOrDefault(operand, 0) + 1
                }
                "D" -> when {
                    map.isEmpty() -> { }

                    operand == 1 -> {
                        val max = map.lastKey()
                        map[max] = map[max]!! - 1

                        if (map[max] == 0) {
                            map.remove(max)
                        }
                    }
                    operand ==  -1 -> {
                        val min = map.firstKey()
                        map[min] = map[min]!! -1

                        if (map[min] == 0) {
                            map.remove(min)
                        }
                    }
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