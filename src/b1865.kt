private val out = System.out.bufferedWriter()// TODO 실패한 문제
fun main() = with(System.`in`.bufferedReader()) {
    val tc = readLine().toInt()
    repeat(tc) {
        val (n, m, w) = readLine().split(" ").map{ it.toInt() }
        val graph = Array(n + 1) { ArrayList<Pair<Int,Int>>() }

        repeat(m) {
            val (s, e, t) = readLine().split(" ").map { it.toInt() }
            graph[s].add(Pair(e, t))
            graph[e].add(Pair(s, t))
        }
        repeat(w) {
            val (s, e, t) = readLine().split(" ").map { it.toInt() }
            graph[s].add(Pair(e, -t))
        }

        var res = false
        for (start in 1..n) {
            val ans = Array(n + 1){ 99000000}
            ans[start] = 0

            println()
            for (i in 1 .. n) {
                for ((pos, cost) in graph[i]) {
                    if(ans[pos] > ans[i] + cost) {
                        ans[pos] = ans[i] + cost
                    }
                }
            }
            println(ans.contentDeepToString())
            println("===================")
            for (i in 1 .. n) {
                for ((pos, cost) in graph[i]) {
                    if(ans[pos] > ans[i] + cost) {
                        ans[pos] = ans[i] + cost// TODO 제출시에는 제거
                        res = true
                        println(ans.contentDeepToString())
                    }
                }
            }
            println("===================")
            println(ans.contentDeepToString())
        }
        out.appendLine(
            if(res) {
                "YES"
            } else {
                "NO"
            }
        )
    }
    out.flush()
}