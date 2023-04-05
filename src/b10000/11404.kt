import java.lang.Integer.min

/*
플로이드 워샬
 */
fun main() {
    val cityCnt = readln().toInt()
    val edgeCnt = readln().toInt()

    val max = 9900001

    val arr = List(cityCnt) {
        IntArray(cityCnt) { max }
    }

    repeat(edgeCnt) {
        val (from, to, cost) = readln().split(" ").map { it.toInt() }
        val fromIdx = from - 1
        val toIdx = to - 1

        arr[fromIdx][toIdx] = min(arr[fromIdx][toIdx], cost)
    }

    fun floydWarshall() {
        for (via in arr.indices) {
            for (start in arr.indices) {
                if (via == start)
                    continue

                for (end in arr[start].indices) {
                    if (via == end)
                        continue
                    if (start == end)
                        continue

                    arr[start][end] = min(arr[start][via] + arr[via][end], arr[start][end])
                }
            }
        }
    }
    floydWarshall()

    println(arr.joinToString("\n") { it.map { if (it == max) 0 else it }.joinToString(" ")})
}