import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()

    val map = Array(size) {
        readLine().split(" ").map { it.toInt() }
    }
    val ans = Array(size) {
        IntArray(size) { Int.MAX_VALUE }
    }
    ans[0][0] = 0

    data class Point(val x:Int, val y:Int)
    data class Node(val pos:Point, val cost:Int)

    val que = PriorityQueue<Node>( compareBy { it.cost } )
    que.offer(Node(Point(0, 0), 0))

    while(que.isNotEmpty()) {
        val now = que.poll()

        if(ans[now.pos.y][now.pos.x] < now.cost)
            continue;

        if (now.pos.y == ans.lastIndex && now.pos.x == ans.last().lastIndex) {
            break;
        }

        fun move(dx:Int, dy:Int) {
            val movedX = now.pos.x + dx
            val movedY = now.pos.y + dy

            if (movedY !in ans.indices || movedX !in ans[movedY].indices)
                return

            val distance = map[movedY][movedX] - map[now.pos.y][now.pos.x]
            val cost = if (distance < 0) 0 else distance + 1
            val newCost = ans[now.pos.y][now.pos.x] + cost

            if (ans[movedY][movedX] > newCost) {
                ans[movedY][movedX] = newCost
                que.offer(Node(Point(movedX, movedY), newCost))
            }
        }

        if (now.pos.y == ans.lastIndex) {
            move(1, 0)
        } else if (now.pos.x == ans.lastIndex) {
            move(0, 1)
        } else {
            move(0, 1)
            move(1, 0)
        }
    }

    println(ans.last().last())
}