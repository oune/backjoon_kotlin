package b1000

import java.util.*
import kotlin.math.sqrt
private val out = System.out.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val testCnt = readLine().toInt()
    val size = 10000
    val che = Array(size) { true }.toBooleanArray()
    che[0] = false
    che[1] = false
    for (i in 2..sqrt(size.toDouble()).toInt()) {
        if (che[i]) {
            var idx = i * i
            while (idx < size) {
                che[idx] = false
                idx += i
            }
        }
    }

    for (i in 2 until 1000) {
        che[i] = false
    }

    repeat(testCnt) {
        val (a, b) = readLine().split(" ").map{ it.toInt() }
        val res = bfs(che, a, b)
        if (res != -1)
            out.appendLine(res.toString())
        else
            out.appendLine("Impossible")
    }
    out.flush()
}

fun bfs(che:BooleanArray, a:Int, b:Int) :Int{
    val que = LinkedList<Pair<Int, Int>>()
    val visited = BooleanArray(che.size){ false }
    que.offer(Pair(a, 0))
    visited[a] = true

    while (que.isNotEmpty()) {
        val t = que.poll()
        val p = t.first.toString().toCharArray()
        visited[t.first] = true

        if (t.first == b) {
            return t.second
        }

        for (i in 0 until 4) {
            for (j in 0 .. 9) {
                val c = p.clone()
                c[i] = '0' + j
                val num = c.concatToString().toInt()
                if (che[num] && !visited[num]) {
                    que.offer(Pair(num, t.second + 1))
                }
            }
        }
    }
    return -1
}