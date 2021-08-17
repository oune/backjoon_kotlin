import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with (BufferedReader(InputStreamReader(System.`in`))) {
    val (a, b) = readLine().split(" ").map { it.toInt() }

    val memo = Array<Boolean>(b + 1) { true }
    memo[0] = false
    memo[1] = false

    (2 .. b).forEach {
        if (memo[it]) {
            var i = 2
            var divide = it * i
            while(divide < b) {
                if (it % divide == 0)
                    memo[it] = false
                divide = it * ++i
            }
        }
    }
}