fun main() = with(System.`in`.bufferedReader()) {
    val (_, m) = readLine().split(" ").map { it.toInt() }
    val trees = readLine().split(" ").map { it.toInt() }

    var left = 0L
    var right = 1000000000L
    var ans = 0L
    while( left <= right ) {
        val mid = (left + right) / 2

        var sum = 0L
        for (tree in trees) {
            if (tree > mid)
                sum += tree - mid
        }

        if (sum >= m) {
            left = mid + 1
            ans = mid
        } else {
            right = mid - 1
        }
    }

    print(ans)
}