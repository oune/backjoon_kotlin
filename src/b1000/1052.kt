import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }

    var num = n.toDouble()
    repeat(k - 1) {
        num -= 2.0.pow(log2(num).toInt()).toInt()
    }

    println((2.0.pow(ceil(log2(num))) - num).toInt())
}