import java.util.LinkedList

fun main() {
    val map = listOf(
        listOf(1, 2),
        listOf(2, 4),
        listOf(5),
        listOf(3),
        listOf(),
        listOf(),
    )

    val enterCnt = IntArray(map.size) { 0 }
    for (list in map) {
        list.forEach {
            enterCnt[it]++
        }
    }

    val que = LinkedList<Int>()
    for (i in enterCnt.indices) {
        if (enterCnt[i] == 0)
            que.offer(i)
    }

    val ans = mutableListOf<Int>()
    while(que.isNotEmpty()) {
        val now = que.poll()

        ans.add(now)

        for (next in map[now]) {
            if (--enterCnt[next] == 0) {
                que.offer(next)
            }
        }
    }
    println(ans)
}