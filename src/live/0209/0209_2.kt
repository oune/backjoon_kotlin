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

        val sum = acc.toMutableList()
        isSelected[depth] = true
        sum += subset(depth + 1, acc)
        isSelected[depth] = false
        sum += subset(depth + 1, acc)

        return sum
    }

    val ans = subset(0, mutableListOf())
    println(ans.sortedBy { it.size })
}