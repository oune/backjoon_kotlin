import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val (nodes, edges) = readLine().split(" ").map { it.toInt() }
    val visions = readLine().split(" ").map { it.toInt() }.toIntArray()

    data class Node(val pos:Int, val cost:Long)
    val map = Array(nodes) {
        LinkedList<Node>()
    }

    repeat(edges) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }

        if (to == visions.lastIndex || visions[to] == 0)
            map[from].offer(Node(to, cost.toLong()))

        if (from == visions.lastIndex || visions[from] == 0)
            map[to].offer(Node(from, cost.toLong()))
    }

    val que = PriorityQueue<Node>( compareBy { it.cost } )
    val ans = LongArray(map.size) { Long.MAX_VALUE }

    que.offer(Node(0, 0))
    ans[0] = 0

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (ans[now.pos] < now.cost)
            continue

        for (moved in map[now.pos]) {
            val cost = ans[now.pos] + moved.cost

            if (ans[moved.pos] > cost) {
                ans[moved.pos] = cost
                que.offer(Node(moved.pos, cost))
            }
        }
    }

    println(if (ans.last() == Long.MAX_VALUE) -1 else ans.last())
}