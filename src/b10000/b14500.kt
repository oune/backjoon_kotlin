package test.b10000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { readLine().split(" ").map { it.toInt() } }
    val moveList = listOf(
        { i:Int, j:Int -> Pair(i + 1, j) },
        { i:Int, j:Int -> Pair(i, j + 1) },
        { i:Int, j:Int -> Pair(i - 1, j) },
        { i:Int, j:Int -> Pair(i, j - 1) }
    )
    val pQue = PriorityQueue<Triple<Int, Int, Int>> { a, b ->
        when {
            a.third < b.third -> 1
            a.third > b.third -> -1
            else -> 0
        }
    }

    var maxSum = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            val visited = Array(n) { BooleanArray(m) { false } }
            var sum = 0
            var count = 0

            pQue.clear()
            pQue.offer(Triple(i, j, get(arr, i, j)))
            while(pQue.isNotEmpty()) {
                val (nowI, nowJ, num) = pQue.poll()
                visited[nowI][nowJ] = true

                count++
                sum += num

                if (count == 4)
                    break

                moveList.forEach {
                    val (newI, newJ) = it(nowI, nowJ)
                    val next = get(arr, newI, newJ)

                    if (next != -1)
                        if (!visited[newI][newJ])
                            pQue.offer(Triple(newI, newJ, next))
                }
            }

            if ( sum > maxSum )
                maxSum = sum
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