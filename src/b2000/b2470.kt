package test.b2000

import kotlin.math.absoluteValue

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val solutions = readLine().split(" ").map { it.toInt() }.sorted()

    var min = Int.MAX_VALUE
    var ans : Pair<Int, Int> = Pair(0, 0)

    var left = 0
    var right = solutions.lastIndex
    while (left < right) {
        val res = (solutions[left] + solutions[right])

        if (res.absoluteValue < min) {
            min = res.absoluteValue
            ans = Pair(solutions[left], solutions[right])
        }

        if (res > 0) {
            right--
        } else {
            left++
        }
    }

    print("${ans.first} ${ans.second}")
}