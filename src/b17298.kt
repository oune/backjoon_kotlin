import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    readLine()
    val num = readLine().split(" ").map{ it.toInt() }

    val a = Stack<Int>()
    val b = Stack<Int>()

    val out = BufferedWriter(OutputStreamWriter(System.out))

    out.flush()
    out.close()
}