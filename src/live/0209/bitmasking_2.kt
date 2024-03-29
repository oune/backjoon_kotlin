fun main() = with(System.`in`.bufferedReader()) {
    val numbs = readLine().split(" ").map { it.toInt() }

    fun Int.isVisited(idx: Int):Boolean {
        return this and (1 shl idx) != 0
    }

    fun List<Int>.subset(): List<List<Int>> {
        val res = mutableListOf<List<Int>>()

        for (masking in 0 until (1 shl numbs.size)) {
            val list = this.filterIndexed { index, _ -> masking.isVisited(index) }
            res.add(list)
        }

        return res
    }

    val list = numbs.subset()
    println(list)
}