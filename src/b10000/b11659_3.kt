package b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (_, m) = readLine().split(" ").map { it.toInt() }

    val nums = readLine().split(" ").map {
        it.toInt()
    }

    val scanned = nums.scan(0) {acc, num ->
        acc + num
    }

    val out = System.out.bufferedWriter()

    repeat(m) {
        val (i, j) = readLine().split(" ").map{ it.toInt() }
        out.appendLine((scanned[j] - scanned[i - 1]).toString())
    }
    out.flush()
}