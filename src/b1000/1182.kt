fun main() = with(System.`in`.bufferedReader()) {
    val (_, s) = readLine().split(" ").map { it.toInt() }
    val numbs = readLine().split(" ").map { it.toInt() }
    val visited = BooleanArray(numbs.size) { false }

    fun List<Int>.subset(depth:Int, acc:List<List<Int>>):List<List<Int>> {
        if (depth == this.size) {
            val list = this.filterIndexed { index, _ ->
                visited[index]
            }.toList()

            return listOf(list)
        }

        visited[depth] = true
        val list1 = this.subset(depth + 1, acc)
        visited[depth] = false
        val list2 = this.subset(depth + 1, acc)

        return list1 + list2
    }

    val ans = numbs.subset(0, listOf()).filter { it.isNotEmpty() }.count { list ->
        list.sum() == s
    }

    println(ans)
}