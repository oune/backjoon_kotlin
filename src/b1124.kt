import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with (BufferedReader(InputStreamReader(System.`in`))) {
    val (a, b) = readLine().split(" ").map { it.toInt() }

    val prime = Array<Boolean>(b + 1) { true }
    prime[0] = false
    prime[1] = false

    (2 .. b).forEach {
        if (prime[it]) {
            var i = 2
            var divide = it * i
            while(divide <= b) {
                prime[divide] = false
                divide = it * ++i
            }
        }
    }

    var underPrimeCount = 0

    (a .. b).forEach {
        var now = it
        var count = 0
        var i = 2
        while (i <= now) {
            if (prime[i]) {
                while (now % i == 0) {
                    count++
                    now /= i
                }
            }
            i++
        }
        if (prime[count]) {
            underPrimeCount++
        }
    }

    print(underPrimeCount)
}