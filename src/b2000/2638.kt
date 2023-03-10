import java.util.LinkedList

/*
bfs, 시뮬레이션
치즈가 녹는 경우 4변중 2변 이상이 만나는 경우 녹음
치즈의 내부는 인정하지 않음
치즈가 꽉 차있을때 개발의 편의성을 위해 맵을 여분을 두고 저장
(0, 0) 에서 부터 bfs 탐색 치즈를 만나면 해당치즈의 count를 올리고 더 진행하지 않음
count > 1 이면 치즈 삭제

시간복잡도 n * m * 4 * (n / 2)
n / 2 = 치즈가 가장 느리게 녹는 경우 모두다 차 있을때 양면이 녹기 시작하므로 크기의 절반 만큼 걸림
>> 2 * 10^6
1초 이내 가능

50분 소요
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = listOf(IntArray(m + 2) { 0 }) + List(n) {
        (listOf(0) + readLine().split(" ").map { it.toInt() } + listOf(0)).toIntArray()
    } + listOf(IntArray(m + 2) { 0 })

    fun bfs(startX:Int, startY:Int) {
        val que = LinkedList<Pair<Int, Int>>()
        que.offer(Pair(startX, startY))

        val visited = List(map.size) { IntArray(map[it].size) { 0 } }
        visited[startY][startX] = -1

        val moves = listOf(
            {from:Pair<Int,Int> -> Pair(from.first + 1, from.second)},
            {from:Pair<Int,Int> -> Pair(from.first - 1, from.second)},
            {from:Pair<Int,Int> -> Pair(from.first, from.second + 1)},
            {from:Pair<Int,Int> -> Pair(from.first, from.second - 1)},
        )

        while(que.isNotEmpty()) {
            val now = que.poll()
            for (move in moves) {
                val (x, y) = move(now)

                if (y !in map.indices || x !in map[y].indices)
                    continue

                if (map[y][x] == 1) {
                    visited[y][x]++

                    if (visited[y][x] > 1)
                        map[y][x] = 0
                }

                if (visited[y][x] == 0) {
                    que.offer(Pair(x, y))
                    visited[y][x] = -1
                }
            }
        }
    }

    var count = 0
    while(map.any{ line -> line.any { it == 1 }}) {
        count++
        bfs(0, 0)
    }
    println(count)
}