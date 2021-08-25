private lateinit var ans: Array<Int>
private lateinit var visit: Array<Boolean>

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    ans = Array(n){0}
    visit = Array(n + 1) { false }
    perm(0, n, m, 0)
}

private fun perm (depth: Int, n:Int, r: Int, pre:Int) {
    if (depth == r){
        for (i in 0 until r) {
            print("${ans[i]} ")
        }
        println()
        return
    }

    for (i in 1..n) {
        if (!visit[i]) {
            if (pre < i) {
                ans[depth] = i
                visit[i] = true
                perm(depth + 1, n, r, i)
                visit[i] = false
            }
        }
    }
}