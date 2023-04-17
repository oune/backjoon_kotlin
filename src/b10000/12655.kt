import kotlin.math.min

/*
* 플로이드 워샬
* 여러개의 최단경로 쿼리가 존재하기 때문에 미리 모든 경로의 최단경로를 저장하거나
* 최단경로를 계산하고 저장하는 것이 맞음
* 노드의 크기는 500 이고
* 쿼리의 크기는 10000
* 따라서 다익스트라로 저장하지 않고 계산한다면 q * (n + v) log v
* v = n * n / 2
* 시간 복잡도 >> 10^8
* 불가능
* 따라서 플로이드 워셜로 미리 구하는 것이 합리적이고, 효율적임.
* */

fun main() {
    val (size, queryCnt) = readln().split(" ").map { it.toInt() }
    val map = List(size) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    for (via in map.indices) {
        for (from in map.indices) {
            if (via == from)
                continue

            for (to in map.indices) {
                if (via == to)
                    continue
                if (from == to)
                    continue

                map[from][to] = min(map[from][to], map[from][via] + map[via][to])
            }
        }
    }

    val res = List(queryCnt) {
        val (from, to, limit) = readln().split(" ").map { it.toInt() }.mapIndexed { index, num ->  if (index < 2) num - 1 else num}
        val time = map[from][to]

        if (time <= limit) "Enjoy other party" else "Stay here"
    }

    println(res.joinToString("\n"))
}