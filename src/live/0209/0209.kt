fun main() = with(System.`in`.bufferedReader()){
    val numbs = readLine().split(" ").filter { it != "" }.map { it.toInt() }
    val isSelected = BooleanArray(numbs.size) { false }

    fun subset(depth:Int) {
        if (depth == numbs.size) {
            val subset = numbs.filterIndexed { index, _ ->
                isSelected[index]
            }.joinToString(" ")

            println(subset)
            return
        }

        isSelected[depth] = true
        subset(depth + 1)
        isSelected[depth] = false
        subset(depth + 1)
    }

    subset(0)
}