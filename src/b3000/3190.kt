import java.util.*

/*
구현, 시뮬레이션
주어진 동작을 전부 수행한뒤 얼마나 시간이 소요되는지 확인하는 문제
시간 복잡도 : 마지막으로 방향을 전환한 이후 맵 끝까지 가는 경우
10000 + 100
10100 따라서 웬만하면 시간은 문제 되지 않음.

1시간 반
중간에 enum class 의 활용하는 방법에 대해서 찾아보다가 시간이 더 오래 결렸음.
얼마나 구현을 깔끔하게 하는지가 중요한듯.


실수한점들
indices 확인할때 순서 잘못넣어서 실패
중간에 출력하는거 깜빡하게 안지웠더니 메모리 초과 발생
조금 이상하긴한데 아마도 foreach 사용할때 데이터 가져오면서 문제가 발생하는듯.

 */
enum class Type {
    EMPTY, APPLE, SNAKE
}
fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val map = List(size) {
        IntArray(size) { Type.EMPTY.ordinal }
    }

    val appleCnt = readLine().toInt()
    repeat(appleCnt) {
        val (y, x) = readLine().split(" ").map { it.toInt() - 1}
        map[y][x] = Type.APPLE.ordinal
    }

    val directionCnt = readLine().toInt()
    data class Node(val time:Int, val direction:Char)
    val directions = LinkedList<Node>();
    repeat(directionCnt) {
        val (time, direction) = readLine().split(" ")
        directions.add(Node(time.toInt(), direction[0]))
    }

    val snake = ArrayDeque<Pair<Int, Int>>()
    map[0][0] = Type.SNAKE.ordinal
    snake.add(Pair(0, 0))

    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(-1, 0, 1, 0)
    var direction = 1

    var time = 0
    while (time < 20000) {
        val head = snake.peek()
        val x = head.first + dx[direction]
        val y = head.second + dy[direction]

        time++
        if (!(y in map.indices && x in map[y].indices))
            break
        if (map[y][x] == Type.SNAKE.ordinal)
            break

        if (map[y][x] != Type.APPLE.ordinal) {
            val tail = snake.removeLast()
            map[tail.second][tail.first] = Type.EMPTY.ordinal
        }

        snake.addFirst(Pair(x, y))
        map[y][x] = Type.SNAKE.ordinal

        if (directions.isNotEmpty() && directions.peek().time == time) {
            val newD = directions.poll()

            val d = if (newD.direction == 'L') -1 else 1
            direction = (direction + 4 + d) % 4
        }
    }

    println(time)
}

fun  List<IntArray>.print() {
    this.forEach {
        println(it.joinToString(" "))
    }
    println()
}