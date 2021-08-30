import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine()
    val alpha = Array(27){0}

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
        var mid = '0'
        alpha.forEachIndexed { index, i ->
            if(i != 0) {
                if (i % 2 == 0) {
                    repeat(i / 2) {
                        half += (index + 'A'.code).toChar()
                    }
                } else {
                    mid = (index + 'A'.code).toChar()
                    repeat(i / 2) {
                        half += (index + 'A'.code).toChar()
                    }
                }
            }
        }

        val res = if (mid != '0') {
            half + mid + half.reversed()
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