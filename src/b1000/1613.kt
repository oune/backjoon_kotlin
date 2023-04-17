import kotlin.math.min

/*
* 선후 관계 파악하기
* 선후 관계를 파악하는 데 쿼리의 개수가 50000 개의 쿼리를 확인해야함.
* 또한 사건의 개수는 400, 선후 관계의 개수는 50000
* 이를 위상정렬로 한다면 (50000 + 400)
* 50000 * 50000 = 25 * 10^8 = 2.5 * 10^ 9 시간초과 불가능
* 이를 플로이드 워샬로 한다면 (50000 + 400^3) = 64,050,000 = 10^7.8
* 가능!
* */

fun main() {
    val (eventCnt, relationCnt) = readln().split(" ").map { it.toInt() }

    val maxValue = eventCnt * eventCnt + 1
    val past = List(eventCnt + 1) {
        IntArray(eventCnt + 1) { maxValue }
    }
    val future = List(eventCnt + 1) {
        IntArray(eventCnt + 1) { maxValue }
    }

    repeat(relationCnt) {
        val (from, to) = readln().split(" ").map { it.toInt() }
        past[from][to] = 1
        future[to][from] = 1
    }

    for (via in past.indices) {
        for (from in past.indices) {
            if (via == from)
                continue

            for (to in past.indices) {
                if (via == to)
                    continue
                if (from == to)
                    continue

                past[from][to] = min(past[from][to], past[from][via] + past[via][to])
                future[from][to] = min(future[from][to], future[from][via] + future[via][to])
            }
        }
    }

    val queryCnt = readln().toInt()
    val ans = List(queryCnt) {
        val (from, to) = readln().split(" ").map { it.toInt() }
        val pastCost = past[from][to]
        val futureCost = future[from][to]

        if (pastCost != maxValue) -1
        else if (futureCost != maxValue) 1
        else 0
    }

    println(ans.joinToString("\n"))
}