import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    readLine()
    val num = readLine().split(" ").reversed().map{ it.toInt() }

    val stack = Stack<Int>()
    num.forEach {
        stack.push(it)
    }

    val out = BufferedWriter(OutputStreamWriter(System.out))

    repeat(num.size) {
        val now = stack.pop()
        var max = -1

        for (num in stack) {
            if (num > now && max == -1) {
                max = num
                break
            }
        }

        out.write(max.toString())
        out.write(" ")
    }

    out.flush()
    out.close()
}