import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {
    val (k, n) = readLine().split(" ").map { it.toInt() }
    val cables = IntArray(k){ readLine().toInt() }

    var left = 1L
    var right = 0x7fffffffL
    var ans = 0L
    while (left <= right) {
        val mid = (left + right)/ 2

        var count = 0L
        for (cable in cables) {
            count += cable.toLong() / mid
        }

        if (count >= n) {
            left = mid + 1
            ans = mid
        } else {
            right = mid - 1
        }
    }

    print(ans)
}