import java.util.Comparator
import java.util.PriorityQueue
import kotlin.system.exitProcess

/*
우선순위큐
우선순위큐 문제인줄 알았으면 안풀었을텐데..


 */
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(" ").map { it.toInt() }
    val beers = List(k) {
        readLine().split(" ").map { it.toLong() }
    }.sortedBy { (_, level) -> level }

    val que = PriorityQueue<Long>()

    var sum = 0L
    for ((score, level) in beers) {
        sum += score
        que.add(score)

        if (que.size > n) {
            sum -= que.poll()
        }

        if (que.size == n && sum >= m) {
            println(level)
            exitProcess(0)
        }
    }

    println(-1)
}