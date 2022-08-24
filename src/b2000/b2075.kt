package test.b2000

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val arr = Array(size) {
        readLine().split(" ").map { it.toInt() }
    }

    val idxArr = IntArray(arr.size) { arr.lastIndex }

    var last = 0
    repeat(size) {
        var max = Integer.MIN_VALUE
        var maxIdx = 0
        for (i in arr.indices) {
            val now = arr[idxArr[i]][i]

            if (now > max) {
                max = now
                maxIdx = i
            }
        }

        last = max
        if (idxArr[maxIdx] != 0)
            idxArr[maxIdx]--
    }

    print(last)
}