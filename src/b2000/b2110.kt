fun main() = with(System.`in`.bufferedReader()) {
    val (n, c) = readLine().split(" ").map{ it.toInt() }

    val homes = IntArray(n) { readLine().toInt() }.sorted()

    var left = 1L
    var right = 10000000000L
    var ans = 0L

    while(left <= right) {
        val mid = (left + right) / 2
        var count = 0

        var pre = homes[0]
        for (home in homes) {
            if (home >= pre + mid) {
                count++
                pre = home
            }
        }

        if (count >= c) {
            left = mid + 1
            ans = mid
        } else {
            right = mid - 1
        }
    }

    print(ans)
}