import java.util.*
import kotlin.collections.ArrayList

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val arr = Array(size){
        readLine().toCharArray()
    }
    val res = ArrayList<Int>()

    for (i in 0 until size) {
        for (j in 0 until size) {
            if (arr[i][j] == '1') {
                res.add(bfs(arr, j, i))
            }
        }
    }

    println(res.size)
    res.sorted().forEach {
        println(it)
    }
}

private val moveList = listOf(
    {p:Pair<Int,Int> -> Pair(p.first + 1, p.second)},
    {p:Pair<Int,Int> -> Pair(p.first - 1, p.second)},
    {p:Pair<Int,Int> -> Pair(p.first, p.second + 1)},
    {p:Pair<Int,Int> -> Pair(p.first, p.second - 1)}
)

private fun bfs(arr:Array<CharArray>, x:Int, y:Int) :Int {
    val que = LinkedList<Pair<Int,Int>>()
    que.offer(Pair(x, y))
    arr[y][x] = '0'

    var count = 0
    while (que.isNotEmpty()) {
        val t = que.poll()
        count++

        for (move in moveList) {
            val moved = move(t)

            if (moved.first in arr.indices && moved.second in arr.indices) {
                if (arr[moved.second][moved.first] == '1') {
                    arr[moved.second][moved.first] = '0'
                    que.offer(moved)
                }
            }
        }
    }

    return count
}