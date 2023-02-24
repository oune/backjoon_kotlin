
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = List(n) {
        mutableListOf<Int>()
    }

    repeat(m) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        map[from].add(to)
        map[to].add(from)
    }
    fun check(): Int {
        for (start in map.indices) {
            val visited = BooleanArray(map.size) { false }

            fun dfs(idx:Int, depth:Int): Boolean {
                if (depth == 5)
                    return true

                for (next in map[idx]) {
                    if (visited[next])
                        continue

                    visited[next] = true
                    if (dfs(next, depth + 1))
                        return true
                    visited[next] = false
                }

                return false
            }

            visited[start] = true

            if (dfs(start, 1))
                return 1
        }

        return 0
    }

    println(check())
}