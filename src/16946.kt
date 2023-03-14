import java.util.LinkedList

/*
그래프 탐색
우선 bfs탐색을 통해 0이 연결된 개수를 확인하고, 개수를 늘려줄 좌표들을 저장
연결된 개수와 좌표들을 이용하여 개수를 증가

시간초과
반복 내부에서 새로운 visited 배열을 초기화 하면서 n^4, 즉 10^12 의 시간복잡도가 발생
틀림 (1%)
나머지 연산을 계산 가운데에서 적용했었는데 오버플로우 가능성이 존재하지 않으므로, 연산이 끝난후 1회만 적용

45분 소요
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (height, _) = readLine().split(" ").map { it.toInt() }

    val map = List(height) {
        readLine().map { it.digitToInt() }.toIntArray()
    }

    val visited = List(map.size) {
        BooleanArray(map[it].size) { false }
    }

    val moves = listOf(
        {p:Pair<Int, Int> -> Pair(p.first + 1,  p.second) },
        {p:Pair<Int, Int> -> Pair(p.first - 1,  p.second) },
        {p:Pair<Int, Int> -> Pair(p.first,  p.second + 1) },
        {p:Pair<Int, Int> -> Pair(p.first,  p.second - 1) },
    )

    for (i in visited.indices) {
        for (j in visited[i].indices) {
            if (visited[i][j])
                continue
            if (map[i][j] > 0)
                continue

            val targets = LinkedList<Pair<Int, Int>>()
            val que = LinkedList<Pair<Int, Int>>()
            que.offer(Pair(j, i))
            visited[i][j] = true

            var count = 0
            while(que.isNotEmpty()) {
                val now = que.poll()

                count++

                for (move in moves) {
                    val moved = move(now)
                    val (x, y) = moved

                    if (!(y in map.indices && x in map[y].indices))
                        continue
                    if (visited[y][x])
                        continue

                    if (map[y][x] == 0)
                        que.offer(moved)
                    else
                        targets.offer(moved)

                    visited[y][x] = true
                }
            }

            for (target in targets) {
                val (x, y) = target
                map[y][x] += count
                visited[y][x] = false
            }
        }
    }

    print(map.joinToString("\n") { line -> line.map { it % 10 }.joinToString("")})
}