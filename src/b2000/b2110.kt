fun main() = with(System.`in`.bufferedReader()) {
    val (n, c) = readLine().split(" ").map{ it.toInt() }

    val homes = IntArray(n) { readLine().toInt() }.sorted()

    var left = 1
    var right = homes.last() - homes.first() + 1
    while(left < right) {
        val mid = (left + right) / 2

        var count = 1

        var pre = homes.first()
        homes.drop(1).forEach { home ->
            if (home >= pre + mid) {
                count++
                pre = home
            }
        }

        if (count < c) {
            right = mid
        } else {
            left = mid + 1
        }
    }

    print(left - 1)
}