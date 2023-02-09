fun main() = with(System.`in`.bufferedReader()){
    val numbs = readLine().split(" ").filter { it != "" }.map { it.toInt() }
    val isSelected = BooleanArray(numbs.size) { false }

    fun subset(depth:Int, acc:List<List<Int>>): List<List<Int>> {
        if (depth == numbs.size) {
            val subset = numbs.filterIndexed { index, _ ->
                isSelected[index]
            }.toList()

            return listOf(subset)
        }

        isSelected[depth] = true
        val selected = subset(depth + 1, acc)
        isSelected[depth] = false
        val notSelected =  subset(depth + 1, acc)

        return selected + notSelected
    }

    fun subset(depth:Int): List<List<Int>>{
        return subset(depth, listOf())
    }

    val ans = subset(0)
    val count = 2
    println(ans.filter { it.size == count }.sortedBy { it.size })
}