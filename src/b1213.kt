import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine()
    val alpha = Array(26){0}

    input.forEach {
        alpha[it.code - 'A'.code]++
    }

    var onlyOne = true
    var isPalindrome = true
    alpha.forEach {
        if (it % 2 == 1) {
            if (onlyOne)
                onlyOne = false
            else
                isPalindrome = false
        }
    }

    val out = BufferedWriter(OutputStreamWriter(System.out))
    var half = ""
    if (isPalindrome) {
        var last = -1
        alpha.forEachIndexed { index, i ->
            if(i != 0) {
                if (i % 2 == 0) {
                    repeat(i / 2) {
                        half += (index + 'A'.code).toChar()
                    }
                } else {
                    last = index
                }
            }
        }

        val res = if (last != -1) {
            repeat(alpha[last] / 2) {
                half += (last + 'A'.code).toChar()
            }
            half + (last + 'A'.code).toChar() + half.reversed()
        } else {
            half + half.reversed()
        }
        out.append(res)
    } else {
        out.append("I'm Sorry Hansoo")
    }
    out.flush()
    out.close()
}