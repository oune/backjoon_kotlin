package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (_, s) = readLine().split(" ").map { it.toInt() }

    var pre = 0
    val arr = readLine().split(" ").map {
        val now = it.toInt()
        pre += now
        pre
    }

    var left = -1
    var right = 0
    var max = Int.MAX_VALUE
    while(right in arr.indices) {
        val sum = arr[right] - if (left < 0) 0 else arr[left]
        if (sum < s) {
            right++
        } else {
            val length = right - left
            if (max > length)
                max = length

            left++
        }
    }

    println(if (max == Int.MAX_VALUE) 0 else max)
}