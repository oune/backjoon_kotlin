fun main() = with(System.`in`.bufferedReader()) {
    val arr = Array(9) {
        readLine().toInt()
    }

    val sum = arr.sum()
    val selected = BooleanArray(arr.size) { false }
    var isContinue = true

    fun comb(depth:Int, count:Int, end:Int) {
        if (!isContinue)
            return

        if (depth == arr.size) {
            if (count == end) {
                var heightSum = 0

                arr.forEachIndexed { idx ,height ->
                    if (selected[idx])
                        heightSum += height
                }

                if (sum - heightSum == 100) {
                    arr.forEachIndexed { idx ,height ->
                        if (!selected[idx])
                            println(height)
                    }

                    isContinue = false
                }
            }
            return
        }

        listOf(false, true).forEach {
            selected[depth] = it
            comb(depth + 1, count + if(it) 1 else 0, end)
        }
    }

    comb(0, 0, 2)
}