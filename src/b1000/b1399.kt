package test.b1000

import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {
    val repeat = readLine().toInt()

    repeat(repeat) {
        val (k, m) = readLine().split(' ').map { it.toInt() }

        val arr = IntArray(10) { -1 }
        var pre = 0
        var goldNumber = 1
        while(true) {
            if (goldNumber != -1)
                break

            while(goldNumber > 9) {
                 goldNumber = goldNumber.toString().fold(0) {
                     acc, c -> acc + c.digitToInt()
                 }
            }
            arr[pre] = goldNumber
            pre = goldNumber
            goldNumber *= m
        }

        println(arr.contentToString())
    }
}