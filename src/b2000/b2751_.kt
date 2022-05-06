package test.b2000

fun main() = with(System.`in`.bufferedReader()) {
    val tryCnt = readLine().toInt()
    val sout = System.out.bufferedWriter()

    Array(tryCnt) {
        readLine().toInt()
    }.sorted().forEach {
        sout.appendLine("$it")
    }
    sout.flush()
    sout.close()
}