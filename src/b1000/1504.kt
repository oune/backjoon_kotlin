import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val (n, e) = readLine().split(" ").map { it.toInt() }

    data class Path (val pos:Int, val price: Int)

    val map = Array(n + 1) {
        val list = LinkedList<Path>()
        list.offer(Path(it, 0))
        list
    }

    repeat(e) {
        val (from, to, price) = readLine().split(" ").map { it.toInt() }
        map[from].add(Path(to, price))
        map[to].add(Path(from, price))
    }

    val inf = 800001
    fun dijkstra(from:Int, to:Int):Int {
        val ans = IntArray(n + 1) { inf }
        ans[from] = 0

        val que = PriorityQueue<Path>( compareBy { it.price })
        que.offer(Path(from, 0))

        while (que.isNotEmpty()) {
            val now = que.poll()

            for (moved in map[now.pos]) {
                val newPrice = ans[now.pos] + moved.price
                if (ans[moved.pos] > newPrice) {
                    ans[moved.pos] = newPrice
                    que.offer(Path(moved.pos, newPrice))
                }
            }
        }

        return ans[to]
    }

    fun getRes():Int {
        val (u, v) = readLine().split(" ").map { it.toInt() }

        var sum = 0
        var from = 1
        listOf(u, v, n).forEach { to ->
            val res = dijkstra(from, to)
            if (res == inf)
                return -1
            sum += res
            from = to
        }
        val res1 = sum

        sum = 0
        from = 1
        listOf(v, u, n).forEach { to ->
            val res = dijkstra(from, to)
            if (res == inf)
                return -1
            sum += res
            from = to
        }
        val res2 = sum

        return res1.coerceAtMost(res2)
    }



    println(getRes())
}