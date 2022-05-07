package test.kakoTest2205

import kotlin.math.max

fun main() {
    println(solution(10, 10, arrayOf(intArrayOf(10, 15, 2, 1, 2), intArrayOf(20, 20, 3, 3, 4))))
}

fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
    var resAlp = alp
    var resCop = cop
    val ways = problems + arrayOf(intArrayOf(0, 0, 1, 0, 1), intArrayOf(0, 0, 0, 1, 1))
    var time = 0

    problems.forEach {
        val (alp_req, cop_req, alp_rwd, cop_rwd, cost) = it
        val alpNeed = alp_req - resAlp
        val copNeed = cop_req - resCop

        val possible = ways.filter { way -> // 할 수 있는 행동 설정
            way[0] <= resAlp && way[1] <= resCop
        }

        val sorted = possible.sortedWith( // 가장 비용이 적은 행동 탐색
            compareBy { (_, _, alp_rwd_, cop_rwd_, cost_) ->
                val res = if (alp_rwd_ != 0) {
                    alpNeed / alp_rwd_ * cost_
                } else {
                    0
                } + if (cop_rwd_ != 0) {
                    copNeed / cop_rwd * cost_
                } else
                    0
                if (res == 0)
                    Int.MAX_VALUE
                else
                    res
            }
        )

        val selected = sorted[0]

        val (_, _, alp_rwd_, cop_rwd_, cost_) = selected
        val alpCycle = if (alp_rwd_ > 0) {
            alpNeed / alp_rwd_
        } else 0
        val copCycle = if (cop_rwd_ > 0) {
            copNeed / cop_rwd_
        } else 0
        val n = max(alpCycle, copCycle)
        time += n * cost_

        resAlp += alp_rwd * n
        resCop += cop_rwd * n
        time += cost
    }

    return time
}