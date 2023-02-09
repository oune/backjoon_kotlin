fun main() = with(System.`in`.bufferedReader()) {
    val numbs = readLine().split(" ").map { it.toInt() }

    fun List<Int>.subset(): List<List<Int>> {
        val res = mutableListOf<List<Int>>()

        for (i in 0 until (1 shl numbs.size)) {
            val list = mutableListOf<Int>()
            for (j in numbs.indices) {
                if (i and (1 shl j) != 0) {
                    list.add(this[j])
                }
            }

            res.add(list)
        }
        return res
    }

    val list = numbs.subset()
    println(list)
}