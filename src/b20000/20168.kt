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

    data class State(val pos:Int, val acc:Int, val max:Int)

    val ans = IntArray(nodeCnt + 1) { Int.MAX_VALUE }
    val que = LinkedList<State>()

    ans[startNode] = 0
    que.offer(State(startNode, 0, 0))

    while(que.isNotEmpty()) {
        val now = que.poll()

        for (moved in map[now.pos]) {
            val state = State(moved.pos, now.acc + moved.cost, moved.cost.coerceAtLeast(now.max))

            if (state.acc > money)
                continue

            if (ans[moved.pos] > state.max) {
                ans[moved.pos] = state.max
                que.offer(state)
            }
        }
    }

    val res = ans[endNode]
    println(if (res == Int.MAX_VALUE) -1 else res)
}