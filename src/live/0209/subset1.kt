fun main() = with(System.`in`.bufferedReader()){
    val numbs = readLine().split(" ").filter { it != "" }.map { it.toInt() }
    val isSelected = BooleanArray(numbs.size) { false }

    fun subset(depth:Int, acc:MutableList<List<Int>>): List<List<Int>> {
        if (depth == numbs.size) {
            val subset = numbs.filterIndexed { index, _ ->
                isSelected[index]
            }.toList()
            acc.add(subset)

            return acc
        }

        isSelected[depth] = true
        subset(depth + 1, acc)
        isSelected[depth] = false
        subset(depth + 1, acc)

        return acc
    }

    val ans = subset(0, mutableListOf())
    println(ans.sortedBy { it.size })
}