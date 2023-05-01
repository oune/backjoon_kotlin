import kotlin.math.min
import kotlin.system.exitProcess

fun main() {
    val n = readln().toLong()
    val numbs = readln().split(" ").map { it.toLong() }

    if (n == 1L) {
        println(numbs.sorted().dropLast(1).sum())
        exitProcess(0)
    }

    val a = 5 * (n - 2) * (n - 2) + 4 * (n - 2)
    val b = 8 * (n - 2) + 4
    val c = 4

    var res = 0L
    val one: Long = numbs.min()
    res += a * one

    var two = Long.MAX_VALUE
    for (i in numbs.indices) {
        for (j in i + 1 .. numbs.lastIndex) {
            if (j + i != 5) {
                two = min(two, numbs[i] + numbs[j])
            }
        }
    }
    res += b * two

    var sum = 0L
    for (i in 0..2) {
        sum += min(numbs[i], numbs[5 - i])
    }
    res += c * sum

    println(res)
}