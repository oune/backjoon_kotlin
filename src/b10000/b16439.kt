package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (users, chickens) = readLine().split(' ').map { it.toInt() }
    val arr = Array(users) {
        readLine().split(' ').map { it.toInt() }
    }

    val visited = BooleanArray(chickens) { false }
    val combs = mutableListOf<List<Int>>()
    fun comb(depth:Int, end:Int, count:Int, endCount:Int) {
        if (depth == end) {
            if (count == endCount) {
                val selected = mutableListOf<Int>()
                visited.forEachIndexed { index, isVisit ->
                    if (isVisit) {
                        selected += index
                    }
                }
                combs += selected
            }
            return
        }
        visited[depth] = true
        comb(depth + 1, end, count + 1, endCount)
        visited[depth] = false
        comb(depth + 1, end, count, endCount)
    }
    comb(0, chickens, 0, 3)

    val max = combs.maxOf { list ->
        arr.sumOf { chickenList ->
            list.maxOf {
                chickenList[it]
            }
        }
    }
    println(max)
}