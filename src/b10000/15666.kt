package b10000fun main() = with(System.`in`.bufferedReader()) {
    val (_, m) = readLine().split(" ").map { it.toInt() }
    val numbs = readLine().split(" ").map { it.toInt() }.sorted()

    val res = HashSet<String>()

    fun List<Int>.tracking(depth:Int, num: Int, acc:String) {
        if (depth == m) {
            if (acc !in res) {
                println(acc)
            }
            res.add(acc)
            return
        }

        this.filter {
            it >= num
        }.forEach {
            this.tracking(depth + 1, it, "$acc$it ")
        }
    }

    fun List<Int>.tracking() {
        this.tracking(0, -1, "")
    }

    numbs.tracking()
}