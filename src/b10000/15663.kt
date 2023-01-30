package b10000fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val numbs = readLine().split(" ").map { it.toInt() }.sorted()

    val visited = BooleanArray(numbs.size + 1) { false }
    val res = HashSet<String>()

    fun List<Int>.tracking(depth:Int, acc:String) {
        if (depth == m) {
            if (acc !in res) {
                println(acc)
            }
            res.add(acc)
            return
        }

        this.forEachIndexed { index, i ->
            if (!visited[index]) {
                visited[index] = true
                this.tracking(depth + 1, "$acc$i ")
                visited[index] = false
            }
        }

    }

    fun List<Int>.tracking() {
        this.tracking(0, "")
    }

    numbs.tracking()
}