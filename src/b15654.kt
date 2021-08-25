private val out = System.out.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    data = readLine().split(" ").map{ it.toInt() }.sorted().toIntArray()
    ans = Array(n){0}
    visit = Array(n){false}

    perm(0, n, m)
    out.flush()
}
private lateinit var data:IntArray
private lateinit var visit:Array<Boolean>
private lateinit var ans:Array<Int>
private fun perm(depth: Int, n: Int, r: Int) {
    if (depth == r) {
        repeat(r) {
            out.append("${ans[it]} ")
        }
        out.newLine()
        return
    }

    repeat(n) {
        if (!visit[it]) {
            ans[depth] = data[it]
            visit[it] = true
            perm(depth + 1, n, r)
            visit[it] = false
        }
    }
}