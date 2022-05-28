package test.line0528

import java.util.*

fun main() {
    println(solution(8, intArrayOf(20, 30), intArrayOf(750, 675)))
    println(solution(19, intArrayOf(40, 30, 20, 10), intArrayOf(1000, 2000, 3000, 4000)))
    println(solution(100, intArrayOf(100, 150), intArrayOf(1, 1000000)))
    println(solution(5, intArrayOf(1, 2, 3, 4, 5), intArrayOf(50, 40, 30, 20, 10)))
}

fun solution(fuel: Int, powers: IntArray, distances: IntArray): Int {
    val que = PriorityQueue { a:Rocket, b:Rocket ->
        - (a.time - b.time)
    }
    for (i in powers.indices) {
        que.offer(Rocket(i,0 , Int.MAX_VALUE))
    }

    repeat(fuel) {
        val rocket = que.poll()
        val newFuel = rocket.fuel + 1
        val power = powers[rocket.id]
        var distance = 0.0
        var time = 0
        while (distance < distances[rocket.id]) {
            val speed = power * newFuel
            distance = if (time < newFuel) {
                time * power / 2.0
            } else {
                val uniformMotionTime = time - newFuel
                newFuel * power / 2.0 + speed * uniformMotionTime
            }

            time++
        }
        que.offer(Rocket(rocket.id, newFuel, time))
    }

    val rocket = que.poll()
    val newFuel = rocket.fuel + 1
    val power = powers[rocket.id]
    var distance = 0.0
    var time = 0
    while (distance < distances[rocket.id]) {
        val speed = power * newFuel
        distance = if (time < newFuel) {
            time * power / 2.0
        } else {
            val uniformMotionTime = time - newFuel
            newFuel * power / 2.0 + speed * uniformMotionTime
        }

        time++
    }

    return time
}

private data class Rocket(val id:Int, val fuel: Int, val time:Int)