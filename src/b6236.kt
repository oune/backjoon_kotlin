fun main() = with(System.`in`.bufferedReader()) {
    val (n , m) = readLine().split(" ").map{ it.toInt() }
    val input = Array(n) { readLine().toInt() }

    var left = input.maxOrNull()!!
    var right = 1000000000
    var ans = 0

    while (left <= right) {
        val mid = left / 2 + right / 2

        var money = 0
        var count = 0
        for (i in input) {
            if (i > money) {
                money = mid - i
                count++
            } else {
                money -= i
            }
        }

        if (count <= m) {
            right = mid - 1
            ans = mid
        } else {
            left = mid + 1
        }
    }

    print(ans)
}