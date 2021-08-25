import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    ans = Array(n){0}
    perm(0, n, m)
    out.flush()
}

private lateinit var ans: Array<Int>
private val out = BufferedWriter(OutputStreamWriter(System.out))
private fun perm(depth:Int, n:Int, r:Int) {
    if (depth == r) {
        repeat(r) {
            out.append("${ans[it]} ")
        }
        out.newLine()
        return
    }

    for (i in 1..n) {
        ans[depth] = i
        perm(depth + 1, n, r)
    }
}