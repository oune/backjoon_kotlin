private val out = System.out.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val tCnt = readLine().toInt()
    repeat(tCnt) {
        readLine()
        val inputs = readLine().split(" ").map { it.toInt() }.toSet()
        readLine()
        readLine().split(" ").map{ it.toInt() }.forEach {
            out.appendLine(
                if (inputs.contains(it))
                    "1"
                else
                    "0"
            )
        }
    }

    out.flush()
}