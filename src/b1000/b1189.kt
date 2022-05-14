package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (r, c, k) = readLine().split(" ").map{ it.toInt() }
    val arr = Array(r) {
        readLine().toCharArray()
    }
}