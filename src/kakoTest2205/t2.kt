package test.kakoTest2205

import java.util.*

fun main() {
    println(solution(intArrayOf(3, 2, 7, 2), intArrayOf(4, 6, 5, 1)))
}

fun solution(queue1: IntArray, queue2: IntArray): Int {
    val left = LinkedList<Int>()
    var leftTotal = 0
    queue1.forEach {
        left.offer(it)
        leftTotal += it
    }

    val right = LinkedList<Int>()
    var rightTotal = 0
    queue2.forEach {
        right.offer(it)
        rightTotal += it
    }

    var answer: Int = -1

    val max = left.size + right.size + 1
    var leftZero = false
    var rightZero = false
    var cnt = 0
    while (cnt < max && !(leftZero && rightZero)) {
        if (leftTotal == rightTotal) {
            answer = cnt
            break
        }
        else if (leftTotal > rightTotal) {
            val num = left.poll()
            leftTotal -= num
            rightTotal += num
            right.offer(num)
        } else { // left < right
            val num = right.poll()
            leftTotal += num
            rightTotal -= num
            left.offer(num)
        }

        if (left.isEmpty())
            leftZero = true
        if (right.isEmpty())
            rightZero = true

        cnt++
    }

    return answer
}