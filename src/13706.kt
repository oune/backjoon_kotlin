import java.math.BigInteger

/*
이분탐색
 */
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toBigInteger()

    var low = BigInteger.ONE
    var high = n
    while(low <= high) {
        val mid = low.add(high).divide(2.toBigInteger())

        val double = mid * mid
        if (double == n) {
            println(mid)
            break
        }

        if (double < n) {
            low = mid + BigInteger.ONE
        } else {
            high = mid - BigInteger.ONE
        }
    }
}