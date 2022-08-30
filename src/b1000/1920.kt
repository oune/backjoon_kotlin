package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val arr = readLine().split(' ').map { it.toInt() }.sorted()
    readLine()

    val out = System.out.bufferedWriter()
    readLine().split(" ").map { it.toInt() }.forEach { target ->
        var left = 0
        var right = arr.lastIndex
        var ans = -1

        while( left <= right) {
            val mid = (left + right) / 2
            if (arr[mid] > target) {
                right = mid - 1
            } else if (arr[mid] < target) {
                left = mid + 1
            } else {
                ans = mid
                break
            }
        }

        out.appendLine((if (ans == -1) 0 else 1).toString())
    }
    out.flush()
}