import kotlin.math.sqrt
private val out = System.out.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map{ it.toInt() }
    val che = Array(n + 1) { true }
    che[0] = false
    che[1] = false
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        var idx = i * i
        while (idx <= n) {
            che[idx] = false
            idx += i
        }
    }
    for (i in m..n) {
        if (che[i])
            out.appendLine("$i")
    }
    out.flush()
}