package b1000

private val out = System.out.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val nums = readLine().split(" ").map { it.toInt() }.toSet()
    readLine()
    readLine().split(" ").map { it.toInt() }.forEach {
        if (nums.contains(it)) {
            out.appendLine("1")
        } else {
            out.appendLine("0")
        }
    }
    out.flush()
}