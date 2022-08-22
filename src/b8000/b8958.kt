package test.b8000

fun main() = with(System.`in`.bufferedReader()) {
    val repeat = readLine().toInt()

    val out = System.out.bufferedWriter()
    repeat(repeat) {
        val now = readLine()

        var sum = 0
        var count = 0
        now.forEach {
            if (it == 'O') {
                count++
                sum += count
            }
            if (it == 'X') {
                count = 0
            }
        }

        out.appendLine(sum.toString())
    }

    out.flush()
    out.close()
}