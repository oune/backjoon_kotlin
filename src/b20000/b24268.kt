package test.b20000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, d) = readLine().split(' ').map { it.toInt() }

    val selected = IntArray(d) { -1 }
    val visited = BooleanArray(d) { false }

    val perms = mutableListOf<List<Int>>()
    fun perm(depth:Int, end:Int) {
        if (depth == end) {
            perms += selected.toList()
            return
        }

        (0 until d).forEach {
            if (!visited[it]) {
                selected[depth] = it

                visited[it] = true
                perm(depth + 1, end)
                visited[it] = false
            }
        }
    }
    perm(0, d)

    val ans = perms.filter { it.first() != 0 }.map { list ->
        list.fold("") { acc, i ->
            acc + i.toString()
        }.toInt(d)
    }.filter { it > n }.minOrNull()

    println(ans ?: "-1")
}