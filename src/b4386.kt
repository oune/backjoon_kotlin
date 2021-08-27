fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n) {
        val (a, b) = readLine().split(" ").map { it.toDouble() }
    }
}