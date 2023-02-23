import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val treeCnt = readLine().toInt()
    val limit = readLine().toInt()
    val holes = List(treeCnt + 1) {
        BooleanArray(21) { false }
    }

    repeat(treeCnt) {
        readLine().split(" ").map { it.toInt() }.drop(1).forEach { hole ->
            holes[it + 1][hole] = true
        }
    }

    val ans = List(treeCnt + 1) {
        IntArray(21) { Int.MAX_VALUE }
    }
    ans[0][1] = 0

    data class Point(val x:Int, val y:Int)
    data class State(val point: Point, val cost:Int)

    val que = LinkedList<State>()
    que.offer(State(Point(0,1), 0))

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (now.point.x == ans.lastIndex)
            continue
        if (ans[now.point.x][now.point.y] < now.cost)
            continue

        val moves = listOf(
            {point:Point -> Point(point.x + 1, point.y)},
            {point:Point -> Point(point.x + 1, point.y + 1)},
            {point:Point -> Point(point.x + 1, point.y - 1)},
            {point:Point -> Point(point.x + 1, if (point.y < 10) point.y * 2 else 20) }
        )

        for (move in moves) {
            val moved = move(now.point)

            if (moved.y in ans[moved.x].indices) {
                if (holes[moved.x][moved.y]) {
                    val nowCost = ans[now.point.x][now.point.y]

                    if (ans[moved.x][moved.y] > nowCost) {
                        ans[moved.x][moved.y] = nowCost
                        que.offer(State(moved, nowCost))
                    }
                }
            }
        }

        val newCost = ans[now.point.x][now.point.y] + 1
        if (newCost <= limit) {
            for (i in 1..20) {
                val moved = Point(now.point.x + 1, i)

                if (holes[moved.x][moved.y]) {
                    if (ans[moved.x][moved.y] > newCost) {
                        ans[moved.x][moved.y] = newCost
                        que.offer(State(moved, newCost))
                    }
                }
            }
        }
    }
    val res = ans.last().min()
    println(if (res == Int.MAX_VALUE) - 1 else res)
}