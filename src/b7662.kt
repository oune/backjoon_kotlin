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
        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())

        repeat(calCount) {
            val line = readLine()!!.split(" ")
            val operator = line[0]
            val operand = line[1].toInt()

            when (operator){
                "I" -> {
                    minHeap.add(operand)
                    maxHeap.add(operand)
                }
                "D" -> when {
                    minHeap.isEmpty() -> { }
                    operand == 1 -> {
                        val max = maxHeap.poll()
                        minHeap.remove(max)
                    }
                    operand ==  -1 -> {
                        val min = minHeap.poll()
                        maxHeap.remove(min)
                    }
                }
            }
        }

        if (maxHeap.isNotEmpty())
            out.append("${maxHeap.poll()} ${minHeap.poll()}\n")
        else
            out.append("EMPTY\n")
    }

    out.flush()
    out.close()
}