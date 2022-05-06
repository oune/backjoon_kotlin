package test.b10000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { readLine().split(" ").map { it.toInt() } }
    val tetrominos = listOf(
        { arr:Array<List<Int>>, windowX: Pair<Int, Int>, windowY: Pair<Int, Int> ->

        }
    )

    var maxSum = 0
    for (i in 0 until n) {
        for (j in 0 until m) {

        }
    }

    print(maxSum)
}

fun get(arr:Array<List<Int>>, i:Int, j:Int) :Int{
    if (i in arr.indices)
        if (j in arr[i].indices)
            return arr[i][j]

    return -1
}