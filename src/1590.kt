import java.util.PriorityQueue

/*

전부다 확인해도 될것 같은데..?
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (busCnt, start) = readLine().split(" ").map { it.toInt() }

    val que = PriorityQueue<Int>()
    repeat(busCnt) {
        val (busStart, interval, count) = readLine().split(" ").map { it.toInt() }

        for (i in 0 until count) {
            val time = busStart + interval * i

            if (time >= start)
                que.add(time)
        }
    }

    if (que.isEmpty())
        println(-1)
    else
        println(que.poll() - start)
}