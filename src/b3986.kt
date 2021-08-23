import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val wordCount = readLine().toInt()
    var res = 0
    repeat(wordCount) {
        val word = readLine()
        if (word.length % 2 == 0) {
            val chars = Stack<Char>()

            word.forEach {
                if (chars.isNotEmpty() && chars.peek() == it) {
                    chars.pop()
                } else {
                    chars.push(it)
                }
            }

            if (chars.isEmpty()) {
               res++
            }
        }

    }

    val out = BufferedWriter(OutputStreamWriter(System.out))
    out.append(res.toString())
    out.flush()
    out.close()
}