import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val start = readLine().toInt(2)
    val end = readLine().toInt(2)

    data class State(val num:Int, val count:Int)

    val que = LinkedList<State>()
    val visited = BooleanArray( (1 shl 11) - 1) { false }

    que.offer(State(start, 0))
    visited[start] = true

    var min = Int.MAX_VALUE
    while(que.isNotEmpty()) {
        val now = que.poll()

        if (now.num == end) {
            min = now.count
            break
        }

        for (i in 0 until now.num.toString(2).lastIndex) {
            val moved = now.num xor (1 shl i)

            if (moved in visited.indices) {
                if (!visited[moved]) {
                    que.offer(State(moved, now.count + 1))
                    visited[moved] = true
                }
            }
        }

        val plus = now.num + 1
        if (plus in visited.indices) {
            if (!visited[plus]) {
                que.offer(State(plus, now.count + 1))
                visited[plus] = true
            }
        }

        val minus = now.num - 1
        if (minus in visited.indices) {
            if (!visited[minus]) {
                que.offer(State(minus, now.count + 1))
                visited[minus] = true
            }
        }
    }
    println(min)
}