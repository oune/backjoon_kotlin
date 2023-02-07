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
//        1≤i,j<n이라면, 상수는 A[i][j+1] 또는 A[i+1][j]로만 건너갑니다.
        if (0 <= now.pos.y && now.pos.x < ans.lastIndex) {
            val distance1 = map[now.pos.y][now.pos.x + 1] - map[now.pos.y][now.pos.x + 1]
            val cost1 = if (distance1 < 0) 0 else distance1 + 1

            if (ans[now.pos.y][now.pos.x + 1] > ans[now.pos.y][now.pos.x] + cost1) {
                ans[now.pos.y][now.pos.x + 1] = ans[now.pos.y][now.pos.x] + cost1
                que.offer(Node(Point(now.pos.x + 1, now.pos.y), cost1))
            }

            val distance = map[now.pos.y + 1][now.pos.x] - map[now.pos.y + 1][now.pos.x]
            val cost = if (distance < 0) 0 else distance + 1

            if (ans[now.pos.y + 1][now.pos.x] > ans[now.pos.y][now.pos.x] + cost) {
                ans[now.pos.y + 1][now.pos.x] = ans[now.pos.y][now.pos.x] + cost
                que.offer(Node(Point(now.pos.x, now.pos.y + 1), cost))
            }
            continue
        }
//        i=n,1≤j<n이라면, A[i][j+1]로만 건너갑니다.
        if (now.pos.y == ans.lastIndex && now.pos.x in ans[now.pos.y].indices) {
            val distance = map[now.pos.y][now.pos.x + 1] - map[now.pos.y][now.pos.x + 1]
            val cost = if (distance < 0) 0 else distance + 1

            if (ans[now.pos.y][now.pos.x + 1] > ans[now.pos.y][now.pos.x] + cost) {
                ans[now.pos.y][now.pos.x + 1] = ans[now.pos.y][now.pos.x] + cost
                que.offer(Node(Point(now.pos.x + 1, now.pos.y), cost))
            }
            continue
        }
//        1≤i<n,j=n이라면 A[i+1][j]로만 건너갑니다.
        if (now.pos.y in ans.indices && now.pos.x == ans.lastIndex) {
            val distance = map[now.pos.y + 1][now.pos.x] - map[now.pos.y + 1][now.pos.x]
            val cost = if (distance < 0) 0 else distance + 1

            if (ans[now.pos.y + 1][now.pos.x] > ans[now.pos.y][now.pos.x] + cost) {
                ans[now.pos.y + 1][now.pos.x] = ans[now.pos.y][now.pos.x] + cost
                que.offer(Node(Point(now.pos.x, now.pos.y + 1), cost))
            }
            continue
        }
    }

    println(ans.last().last())
}