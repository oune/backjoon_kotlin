package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, l, w, h) = readLine().split(" ").map{ it.toInt() }

    var low = 0.0
    var high = l.coerceAtLeast(w.coerceAtLeast(h)).toDouble()

    for (i in 0..10000) {
        val mid = (low + high) / 2.0
        val cnt = (l / mid).toLong() * (w / mid).toLong() * (h / mid).toLong()

        if (cnt >= n) {
            low = mid
        } else {
            high = mid
        }
    }

    print(low)
}