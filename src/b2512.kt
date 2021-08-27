fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val cities = readLine().split(" ").map { it.toInt() }
    val m = readLine().toInt()

    var left = 1
    var right = cities.maxOrNull()!!
    var ans = 0
    while (left <= right) {
        val mid = (left + right) / 2

        var sum = 0
        for (city in cities) {
            if (city > mid) {
                sum += mid
            } else {
                sum += city
            }
        }

        if (sum <= m) {
            left = mid + 1
            ans = mid
        } else {
            right = mid - 1
        }
    }
    print(ans)
}