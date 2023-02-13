import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (nodeCnt, edgeCnt, startNode, endNode, money) = readLine().split(" ").map { it.toInt() }

    data class Node(val pos: Int, val cost: Int)

    val map = List(nodeCnt + 1) {
        LinkedList<Node>()
    }
    repeat(edgeCnt) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        map[from].add(Node(to, cost))
        map[to].add(Node(from, cost))
    }

    val ans = IntArray(nodeCnt + 1) { Int.MAX_VALUE }
    val min = IntArray(nodeCnt + 1) { Int.MAX_VALUE }
    val max = IntArray(nodeCnt + 1) { -1 }
    val que = LinkedList<Node>()

    ans[startNode] = 0
    max[startNode] = 0
    min[startNode] = 0
    que.offer(Node(startNode, 0))

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (ans[now.pos] < now.cost)
            continue

        for (moved in map[now.pos]) {
            val newCost = ans[now.pos] + moved.cost

            if (newCost > money)
                continue

            if (ans[moved.pos] > newCost) {
                ans[moved.pos] = newCost
                max[moved.pos] = max[now.pos].coerceAtLeast(moved.cost)
                min[moved.pos] = min[moved.pos].coerceAtMost(max[moved.pos])
                que.offer(Node(moved.pos, ans[moved.pos]))
            }
        }
    }

    val totalCost = ans[endNode]
    val res = if (totalCost != Int.MAX_VALUE  && totalCost <= money) min[endNode] else -1

    println(res)
}