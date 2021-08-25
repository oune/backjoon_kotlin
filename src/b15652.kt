private val out = System.out.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map{ it.toInt() }
    ans = Array(n){0}

    perm(0, n, m, 0)
    out.flush()
}
private lateinit var ans: Array<Int>
private fun perm(depth: Int, n: Int, m: Int, pre:Int) {
    if (depth == m) {
        repeat(m) {
            out.append("${ans[it]} ")
        }
        out.newLine()
        return
    }

    for (i in 1..n) {
        if (pre <= i) {
            ans[depth] = i
            perm(depth+1, n, m, i)
        }
    }
}