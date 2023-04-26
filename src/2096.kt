import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

/*
* dp
* 내려가면서 최소와 최대를 유지하도록 */
fun main() {
    val n = readln().toInt()

    val arr = List(n) {
        readln().split(" ").map { it.toInt() }
    }
    val min = List(arr.size) {
        IntArray(arr[it].size) { Int.MAX_VALUE }
    }

    val max = List(arr.size) {
        IntArray(arr[it].size) { 0 }
    }

    for (i in arr.first().indices) {
        val value = arr.first()[i]
        min[0][i] = value
        max[0][i] = value
    }

    val moveList = listOf(
        {idx:Int -> idx + 1},
        {idx:Int -> idx},
        {idx:Int -> idx - 1},
    )

    for (depth in 1 .. arr.lastIndex) {
        val pre = depth - 1

        for (idx in arr[depth].indices) {
            for (move in moveList) {
                val moved = move(idx)

                if (moved !in arr[depth].indices)
                    continue

                min[depth][idx] = min(min[depth][idx], min[pre][moved] + arr[depth][idx])
                max[depth][idx] = max(max[depth][idx], max[pre][moved] + arr[depth][idx])
            }
        }
    }

    println("${max.last().max()} ${min.last().min()}")
}