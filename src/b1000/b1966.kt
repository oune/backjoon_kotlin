package test.b1000

import java.util.*

fun main() = with(System.`in`.bufferedReader()){
    val repeat = readLine().toInt()
    repeat(repeat) {
        var (_, targetIdx) = readLine().split(" ").map { it.toInt() }

        val que = LinkedList<Int>()
        readLine().split(" ").forEach { que.offer(it.toInt()) }

        var sorted = que.sortedDescending()
        var order = 0
        while(que.isNotEmpty()) {
            val priority = que.poll()
            targetIdx--

            if (priority < sorted.first()) {// 우선순위가 낮은 경우
                que.offer(priority)

                if (targetIdx == -1) {
                    targetIdx = que.lastIndex
                }
            } else {
                sorted = sorted.drop(1)
                order++
            }

            if (targetIdx == -1) {// 타겟이 나온경우
                println(order)
                break
            }
        }
    }
}