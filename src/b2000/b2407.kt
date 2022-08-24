package test.b2000

import java.math.BigInteger

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val minM = m.coerceAtMost(n - m)

    var res = BigInteger.ONE
    var divider = BigInteger.ONE
    var num = BigInteger(n.toString())

    repeat(minM) {
        res = res.multiply(num)
        res = res.divide(divider)
        num = num.minus(BigInteger.ONE)
        divider = divider.plus(BigInteger.ONE)
    }

    print(res)
}