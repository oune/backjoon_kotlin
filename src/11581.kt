/*
* 플로이드 워샬
* 사이클이 존재하는지를 확인해야하는 플로이드 워샬
* 단 갈수 없는 곳에 존재하는 사이클이 아니리면 사이클로 생각하지 않음.
*
* 런타임 에러 (NumberFormat)(1%)
* 테스트케이스에 공백이 존재
*
* ❌(52%)
반례 https://www.acmicpc.net/board/view/80025
5
1
2
1
5
1
4
2
3 5
ans: NO CYCLE
* */
fun main() {
    val n = readln().toInt()
    val inf = Int.MAX_VALUE / 3

    val map = List(n + 1) {
        IntArray(n + 1) {
            inf
        }
    }

    repeat(n - 1) { i ->
        readln()
        val destinations = readln().split(" ").filter { it != "" }.map { it.toInt() }

        for (j in destinations) {
            map[i + 1][j] = 1
        }
    }

    for (via in map.indices) {
        for (from in map.indices) {
            if (via == from)
                continue
            for (to in map.indices) {
                if (via == to)
                    continue

                map[from][to] = minOf(map[from][to], map[from][via] + map[via][to])
            }
        }
    }


    val ways = map[1].withIndex().filter { (index, value) ->
        value != inf
    }.map { it.index }

    var isCycle = false
    for (i in map.indices) {
        if (map[i][i] != inf) {
            if (i in ways)
                isCycle = true
        }
    }
    println(if (isCycle) "CYCLE" else "NO CYCLE")
}