package b1000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val percentages = readLine().split(' ').map { it.toInt() }

    fun permutationsRecursive(input: List<Int>, index: Int, answers: MutableList<List<Int>>) {
        if (index == input.lastIndex) answers.add(input.toList())
        for (i in index .. input.lastIndex) {
            Collections.swap(input, index, i)
            permutationsRecursive(input, index + 1, answers)
            Collections.swap(input, i, index)
        }
    }

    fun permutations(input: List<Int>): List<List<Int>> {
        val solutions = mutableListOf<List<Int>>()
        permutationsRecursive(input, 0, solutions)
        return solutions
    }

    val permuted = permutations(percentages)

    var max = 0
    for (point in permuted) {
        val newList = point.scan(0) { sum, num ->
            sum + num
        }.toList()

        var count = 0
        for (i in 0 until newList.lastIndex) {
            for (j in i until newList.lastIndex) {
                if (newList[j] - newList[i] == 50)
                    count++
            }
        }

        max = max.coerceAtLeast(count)
    }

    println(max)
}