import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val numCnt = readLine().toInt()

    val sb = StringBuilder()
    val left = PriorityQueue<Int>(compareBy { -it })
    val right = PriorityQueue<Int>(compareBy { it })

    repeat(numCnt) {
        val now = readLine().toInt()

        right.offer(now)

        if (left.size != right.size) {
            left.offer(right.poll())
        }

        if (left.isNotEmpty() && right.isNotEmpty()) {
            if (left.peek() > right.peek()) {
                val l = left.poll()
                val r = right.poll()
                left.offer(r)
                right.offer(l)
            }
        }

        if (left.size == right.size) {
            sb.appendLine(left.peek().coerceAtMost(right.peek()))
        } else {
            sb.appendLine(left.peek())
        }
    }

    print(sb)
}