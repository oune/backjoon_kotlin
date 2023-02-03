import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    System.out.bufferedWriter().run {
        repeat(readLine().toInt()) {
            val (computerCnt, lineCnt, hacked) = readLine().split(" ").map { it.toInt() }
            data class Point(val pos:Int, val cost:Int)

            val ans = Array(computerCnt + 1) { Int.MAX_VALUE }
            val map = Array(ans.size) { LinkedList<Point>() }

            repeat(lineCnt) {
                val (to, from, price) = readLine().split(" ").map { it.toInt() }
                map[from].offer(Point(to, price))
            }

            val que = LinkedList<Point>()
            que.offer(Point(hacked, 0))
            ans[hacked] = 0

            while(que.isNotEmpty()) {
                val now = que.poll()

                map[now.pos].filter { moved ->
                    ans[moved.pos] > ans[now.pos] + moved.cost
                }.forEach { moved ->
                    ans[moved.pos] = ans[now.pos] + moved.cost
                    que.offer(Point(moved.pos, ans[moved.pos]))
                }
            }

            appendLine("${ans.filter { it != Int.MAX_VALUE }.size } ${ ans.filter { it != Int.MAX_VALUE }.max() }")
        }
        flush()
    }

}