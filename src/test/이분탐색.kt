package test.test

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }

    val target = 99
    var low = 0
    var high = arr.lastIndex
    var ans = 0

    while(low <= high) {
        val mid = (low + high) / 2
        if (arr[mid] > target) {
            high = mid - 1
        } else if (arr[mid] < target) {
            low = mid + 1
        } else {
            ans = mid
            break
        }
    }
}