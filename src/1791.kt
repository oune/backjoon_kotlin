import java.util.PriorityQueue

/*
그리디
*/

fun main() {
    val n = readln().toInt()
    data class Ramen(val deadLine:Int, val count:Int)
    val ramens = List(n) {
        val (deadLine, count) = readln().split(" ").map { it.toInt() }
        Ramen(deadLine, count)
    }.sortedWith( compareBy<Ramen> { it.deadLine }.thenBy { -it.count })

    val que = PriorityQueue<Int>()

    for ((deadLine, count) in ramens) {
        if (que.size < deadLine) {
            que.add(count)
        } else {
            val num = que.poll()
            que.add(if (num > count) num else count)
        }
    }

    println(que.sum())
}