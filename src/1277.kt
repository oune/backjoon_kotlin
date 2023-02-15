import java.util.LinkedList
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val (powerPlantCnt, lineCnt) = readLine().split(" ").map { it.toInt() }
    val limit = readLine().toDouble()

    data class Point(val x:Int, val y:Int)
    data class State(val pos:Int, val cost:Double)

    fun Point.getDistance(point: Point): Double {
        val dx = this.x - point.x
        val dy = this.y - point.y
        return sqrt( (dx * dx + dy * dy).toDouble() )
    }

    val powerPlants = listOf(Point(-1, -1)) + List(powerPlantCnt) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        Point(x, y)
    }

    val map = List(powerPlants.size) {
        LinkedList<Int>()
    }

    repeat(lineCnt) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        map[from].add(to)
        map[to].add(from)
    }

    val que = LinkedList<State>()
    que.offer(State(1, 0.0))

    val ans = DoubleArray(powerPlants.size) { Double.MAX_VALUE }
    ans[1] = 0.0

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (ans[now.pos] < now.cost)
            continue

        map[now.pos].forEach { moved ->
            val newCost = ans[now.pos]

            if (ans[moved] > newCost) {
                ans[moved] = newCost
                que.offer(State(moved, ans[moved]))
            }
        }

        powerPlants.forEachIndexed { index, moved ->
            if (index != 0 && index != now.pos) {
                val nowPowerPlant = powerPlants[now.pos]
                val distance = nowPowerPlant.getDistance(moved)

                if (distance <= limit) {
                    val newCost = ans[now.pos] + distance

                    if (ans[index] > newCost) {
                        ans[index] = newCost
                        que.offer(State(index, ans[index]))
                    }
                }
            }
        }
    }

    print((if (ans.last() == Double.MAX_VALUE) - 1 else ans.last() * 1000).toInt())
}