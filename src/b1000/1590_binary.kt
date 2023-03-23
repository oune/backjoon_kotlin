import java.util.PriorityQueue

/*
이분탐색 이용
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (busCnt, start) = readLine().split(" ").map { it.toInt() }

    val list = mutableListOf<Int>()
    repeat(busCnt) {
        val (busStart, interval, count) = readLine().split(" ").map { it.toInt() }

        for (i in 0 until count)
            list.add(busStart + interval * i)
    }

    val sorted = list.sorted()
    val idx = sorted.binarySearch(start).let { if (it < 0) -it - 1 else it }

    if (idx !in sorted.indices)
        println(-1)
    else
        println(sorted[idx] - start)
}