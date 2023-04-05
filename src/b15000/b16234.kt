package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, l, r) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) {
        readLine().split(" ").map{ it.toInt() }.toIntArray()
    }


}