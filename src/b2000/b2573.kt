package test.b2000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map{ it.toInt() }
    val arr = Array(n) {
        readLine().split(" ")
    }


}