package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val bridges = Array(m) {
        readLine().split(" ").map{ it.toInt() }
    }
}