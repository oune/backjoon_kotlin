import java.util.PriorityQueue
import kotlin.math.cos
import kotlin.math.min

/*다익스트라
*방향이 바뀌는 경우의 개수를 최소로 하는 최단경로
*비슷한 문제중 거울을 둘수 없는 장소를 추가하여 일반적인 다이스트라랑 다르게 푸는 문제도 존재
* 해당문제에서는 한번 방문한 장소는 어떤 방향으로 도착했는지 불구하고, 더 높은 경로로 지난후 최단경로가 나타나는 경우가 발생하지 않음
* 따라서 다른 문제에서 사용한 3차원 배열을 이용한 해결이 필요가 없을 줄 알았는데 무한 루프를 도는 경우가 있기에 3차원으로 하는 것이 맞음
* 또한 거울을 90도로 굴절시키는데 다시 되돌아가는 경우는 비용이 증가하기 때문에 문제 되지 않음
* 시간 복잡도는 O(E log V)
* E = 정점, V는 간선
* 우선순위큐를 사용하였기 때문에 Log V 의 시간복잡도
*
* 틀렸습니다. (50%)
* 반례
* 출처 https://bingorithm.tistory.com/2
* 15 10
...*...***.C..*
.*.*.*........*
.*...*...*....*
.*.*....****.**
.*..**........*
.**..********.*
.*...*...*..*.*
.**..***.*.**.*
C........*.....
..***..........
답: 6
* 비용이 작았을때만 업테이트 하는것이아니라 같았을때도 확인이 필요함.
*
* 메모리초과 (80%)
* */
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = List(m) {
        readln().toList()
    }
    data class Point(val x:Int, val y:Int)
    val points = mutableListOf<Point>()
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == 'C')
                points.add(Point(j, i))
        }
    }

    val start = points.first()

    val costs = List(m) {
        List(n) {
            IntArray(4) { Int.MAX_VALUE }
        }
    }
    data class State(val pos:Point, val cost:Int, val direction:Int)
    val que = PriorityQueue<State>(compareBy { it.cost })

    val moves = listOf(
        { point:Point -> Point(point.x + 1, point.y) },
        { point:Point -> Point(point.x - 1, point.y) },
        { point:Point -> Point(point.x, point.y + 1) },
        { point:Point -> Point(point.x, point.y - 1) },
    )
    
    for (direction in moves.indices) {
        que.add(State(start, 0, direction))
        costs[start.y][start.x][direction] = 0
    }


    while(que.isNotEmpty()) {
        val (now, cost, pre) = que.poll()

        if (costs[now.y][now.x][pre] < cost)
            continue

        for (direction in moves.indices) {
            val move = moves[direction]
            val (x, y) = move(now)

            if (y !in map.indices || x !in map[y].indices)
                continue
            if (map[y][x] == '*')
                continue

            val newCost = cost + if (pre == direction) 0 else 1
            if (costs[y][x][direction] > newCost) {
                costs[y][x][direction] = newCost
                que.add(State(Point(x, y), newCost, direction))
            }
        }
    }

    val end = points.last()
    println(costs[end.y][end.x].min())
//
//    println(points)
//    println(map.joinToString("\n") { line ->
//        line.joinToString(" ")
//    })
//
//    println()
//    println(costs.joinToString("\n") { line ->
//        line.joinToString(" ") {
//            if (it.min() == Int.MAX_VALUE) "X\t" else "${it.min()}\t"
//        }
//    })
}