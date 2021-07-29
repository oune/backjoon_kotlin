import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val testCase = readLine()!!.toInt()
    val out = BufferedWriter(OutputStreamWriter(System.out))

    repeat(testCase) {
        val calCount = readLine()!!.toInt()
        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())

        repeat(calCount) {
            val tokenizer = StringTokenizer(readLine())
            val operator = tokenizer.nextToken()
            val operand = tokenizer.nextToken().toInt()

            if (operator == "I") {
                minHeap.add(operand)
                maxHeap.add(operand)
            } else if (minHeap.isNotEmpty() && operator == "D") {
                if (operand == 1) {
                    val max = maxHeap.poll()
                    minHeap.remove(max)
                } else if (operand == -1) {
                    val min = minHeap.poll()
                    maxHeap.remove(min)
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