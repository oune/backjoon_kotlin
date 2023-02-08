fun main() = with(System.`in`.bufferedReader()) {
    val numbs = List(9) {
        readLine().toInt()
    }
    var ans = intArrayOf()
    val selected = IntArray(7) { 0 }
    fun comb(count:Int, depth:Int) {
        if (depth !in numbs.indices)
            return

        if (count == 7) {
            println(selected.joinToString(" "))
            if (selected.sum() == 100) {
                ans = selected.clone()
            }
            return
        }

        for (i in depth .. numbs.lastIndex) {
            selected[count] = numbs[i]
            comb(count + 1, i + 1)
        }
    }
    comb(0, 0)

    print(ans.joinToString("\n"))
}