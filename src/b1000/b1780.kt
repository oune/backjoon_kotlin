package b1000

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = Array(n) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    val res = rec(arr, n - 1, n - 1, n)
    print("${res.first}\n${res.second}\n${res.third}")
}

fun rec(arr:Array<IntArray>, i:Int, j:Int, size:Int) :Triple<Int, Int, Int> {
    if (size == 1)
        return when(arr[i][j]) {
            -1 -> return Triple(1, 0, 0)
            0 -> return Triple(0, 1, 0)
            1 -> return Triple(0, 0, 1)
            else -> return Triple(0, 0,0)
        }

    val newSize = size / 3

    val sum = listOf(rec(arr, i, j, newSize)
        , rec(arr, i - newSize, j, newSize)
        , rec(arr, i - newSize * 2, j, newSize)
        , rec(arr, i, j - newSize, newSize)
        , rec(arr, i - newSize, j - newSize, newSize)
        , rec(arr, i - newSize * 2, j - newSize, newSize)
        , rec(arr, i, j - newSize * 2, newSize)
        , rec(arr, i - newSize, j - newSize * 2, newSize)
        , rec(arr, i - newSize * 2, j - newSize * 2, newSize)).fold(Triple(0, 0, 0)) {
            acc, triple ->
            Triple(acc.first + triple.first, acc.second + triple.second, acc.third + triple.third)
    }
    return when (sum) {
        Triple(9, 0, 0) -> Triple(1, 0, 0)
        Triple(0, 9, 0) -> Triple(0, 1, 0)
        Triple(0, 0, 9) -> Triple(0, 0, 1)
        else -> sum
    }
}