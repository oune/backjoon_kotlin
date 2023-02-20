import java.util.LinkedList
import java.util.PriorityQueue
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val (powerPlantCnt, lineCnt) = readLine().split(" ").map { it.toInt() }
    val limit = readLine().toDouble()
    data class PowerPlant(val x:Long, val y:Long)
    data class State(val pos:Int, val cost:Double)

    fun PowerPlant.distance(powerPlant: PowerPlant): Double {
        val dx = this.x - powerPlant.x
        val dy = this.y - powerPlant.y

        return sqrt((dx * dx + dy * dy).toDouble())
    }

    val powerPlants = listOf(PowerPlant(0, 0)) + List(powerPlantCnt) {
        val (x, y) = readLine().split(" ").map { it.toLong() }
        PowerPlant(x, y)
    }

    val map = List(powerPlants.size) {
        mutableListOf<Int>()
    }

    repeat(lineCnt) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        map[from].add(to)
        map[to].add(from)
    }

    val ans = DoubleArray(powerPlants.size) { Double.MAX_VALUE }
    ans[1] = 0.0;

    val que = PriorityQueue<State>( compareBy<State> { it.cost }.thenBy { it.pos })
    que.offer(State(1, 0.0))

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (ans[now.pos] < now.cost)
            continue

        for (moved in map[now.pos]) {
            if (ans[moved] > ans[now.pos]) {
                ans[moved] = ans[now.pos]
                que.offer(State(moved, ans[moved]))
            }
        }

        for (idx in 1 .. powerPlants.lastIndex) {
            val to = powerPlants[idx]
            val distance = powerPlants[now.pos].distance(to)

            if (idx == now.pos)
                continue

            if (distance < limit) {
                val acc = ans[now.pos] + distance

                if (ans[idx] > acc) {
                    ans[idx] = acc
                    que.offer(State(idx, ans[idx]))
                }
            }
        }
    }

    println((ans.last() * 1000).toLong())
}