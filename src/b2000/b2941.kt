import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine()
    var prepre = '2'
    var pre = '1'
    var count = 0
    input.forEach {
        when(it) {
            '=' -> {
                when {
                    prepre == 'd' && pre == 'z' -> {
                        count--
                    }
                    pre == 'z' -> { }
                    pre == 'c' -> { }
                    pre == 's' -> { }
                    else -> count++
                }
            }
            'j' -> {
                when (pre) {
                    'n' -> { }
                    'l' -> { }
                    else -> {
                        count++
                    }
                }
            }
            '-' -> {
                when (pre) {
                    'c' -> { }
                    'd' -> { }
                    else -> {
                        count++
                    }
                }
            }
            else -> {
                count++
            }
        }
        prepre = pre
        pre = it
    }

    print(count)
}