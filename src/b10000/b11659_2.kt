package b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (_, m) = readLine().split(" ").map { it.toInt() }
    var sum = 0
    val nums = listOf(0) + readLine().split(" ").map {
        sum += it.toInt()
        sum
    }

    val out = System.out.bufferedWriter()
    repeat(m) {
        val (i, j) = readLine().split(" ").map{ it.toInt() }
        out.appendLine((nums[j] - nums[i - 1]).toString())
    }
    out.flush()
}