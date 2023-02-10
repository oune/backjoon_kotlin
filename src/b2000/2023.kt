import java.lang.StringBuilder
import kotlin.math.pow
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()

    val start = 10.0.pow(size.toDouble() - 1).toInt()
    val end = 10.0.pow(size.toDouble()).toInt()

    fun Int.isPrime():Boolean {
        val last = sqrt(this.toDouble()).toInt()

        if (this < 2)
            return false

        for (i in 2 .. last) {
            if (this % i == 0)
                return false
        }

        return true
    }

    fun Int.isNotPrime():Boolean {
        return !this.isPrime()
    }

    val sb = StringBuilder()
    for (target in start until end) {
        var divider = start
        var isMagicNumber = true

        while(divider > 0) {
            val now = target / divider

            if (now.isNotPrime()) {
                isMagicNumber = false
                break
            }

            divider /= 10
        }

        if (isMagicNumber) {
            sb.appendLine(target)
        }
    }

    print(sb)
}