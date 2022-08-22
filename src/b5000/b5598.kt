package test.b5000

fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine()
    val out = System.out.bufferedWriter()
    input.forEach {
        out.append(((it.code - 'A'.code + 23) % 26 + 'A'.code).toChar())
    }
    out.flush()
}