package b10000

import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    var nums = readLine().split(' ').filter { it != "" }.map { it.toInt() }
    var size = 0

    val lis = mutableListOf(nums[0])
    nums = nums.drop(1)
    size++


    nums.forEach { key ->
        if (lis[size - 1] < key) {
            lis += key
            size++
        } else {
            val idx = lis.binarySearch(key)
            if (idx > 0)
                lis[idx] = key
            else {
                val upper = (abs(idx) - 1).coerceAtLeast(0)
                lis[upper] = key
            }
        }
    }

    println(size)
}