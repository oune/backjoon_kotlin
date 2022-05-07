package test.b2000

fun main() = with(System.`in`.bufferedReader()) {
    val tryCnt = readLine().toInt()
    val sout = System.out.bufferedWriter()

    IntArray(tryCnt) {
        readLine().toInt()
    }.sorted().forEach {
        sout.appendLine(it.toString())
    }
    sout.flush()
    sout.close()
}