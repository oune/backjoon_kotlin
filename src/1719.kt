/*
플로이드 워샬
플로이드 워샬은 고정인데
출처를 적어야 하는 문제

런타임 에러 (IllegalArgument) 8%

 */
fun main() {
    val (size, lineCnt) = readln().split(" ").map { it.toInt() }

    val maxValue = size * size * 2001
    val map = List(size) {
        IntArray(size) { maxValue }
    }
    val table = List(size) {
        CharArray(size) { '-' }
    }

    repeat(lineCnt) {
        val (from, to, cost) = readln().split(" ").map { it.toInt() }
        map[from - 1][to - 1] = cost
        map[to - 1][from - 1] = cost

        table[from - 1][to - 1] = to.digitToChar()
        table[to - 1][from - 1] = from.digitToChar()
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

                val viaCost = map[from][via] + map[via][to]
                if (viaCost < map[from][to]) {
                    map[from][to] = viaCost
                    table[from][to] = table[from][via]
                }
            }
        }
    }

    println(table.joinToString("\n") {
        it.joinToString(" ")
    })
}