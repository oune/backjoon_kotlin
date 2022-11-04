package test

import kotlin.math.abs

fun main() {
    val numbers = listOf(1, 10)
    val target = 5
    val idx = numbers.binarySearchBy(target) { it }
    println(idx)
    println((abs(idx) - 2).coerceAtLeast(0))
    println(numbers.size + idx)
}