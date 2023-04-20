import java.lang.Exception
import kotlin.math.min

/*
플로이드 워셜
비용 = 길이 * 길이

20분 소요
* */
fun main() {
    val testCaseCnt = readln().toInt()

    val ans = List(testCaseCnt) {
        val (n, p, q) = readln().split(" ").map { it.toInt() }
        val humans = List(n) {
            readln().toInt() - 1
        }

        val maxValue = Int.MAX_VALUE.toLong()

        val map = List(p) {
            LongArray(p) { maxValue }
        }

        repeat(q) {
            val (from, to, cost) = readln().split(" ").map { it.toInt() }
                .mapIndexed { index, num -> if(index < 2) num - 1 else num}
            map[from][to] = cost.toLong()
            map[to][from] = cost.toLong()
        }

        for (via in map.indices) {
            for (from in map.indices) {
                if (via == from)
                    continue

                for (to in map.indices) {
                    if (to == via)
                        continue
                    if (to == from) {
                        map[from][to] = 0L
                        continue
                    }

                    map[from][to] = min(map[from][to], map[from][via] + map[via][to])
                }
            }
        }

        val distances = List(map.size) { to ->
            var sum = 0L

            var isError = false
            for (human in humans) {
                val cost = map[human][to]
                if (cost == Int.MAX_VALUE.toLong())
                    isError = true
                sum += cost * cost
            }


            Pair(to + 1, if (isError) Long.MAX_VALUE else sum)
        }

        val sorted = distances.sortedBy { it.second }
        val res = sorted.first()

        "${res.first} ${res.second}"
    }

    println(ans.joinToString("\n"))
}