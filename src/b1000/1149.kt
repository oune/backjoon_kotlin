package b1000fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val list = List(size) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    var pre = listOf(0, 0, 0)
    val ans = list.map { intArray ->
        val save = intArray.mapIndexed { index, num ->
            num + pre.filterIndexed { idx, _ ->
                idx != index
            }.min()
        }
        pre = save
        save
    }

    println(ans.last().min())
}