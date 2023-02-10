fun main() = with(System.`in`.bufferedReader()) {
    val (problemCnt, studentCnt) = readLine().split(" ").map { it.toInt() }

    val students = List(studentCnt) {
        readLine().split(" ").map { it.toInt() }.drop(1)
    }

    fun Int.visit(idx:Int):Int {
        return this or (1 shl idx)
    }

    fun Int.allVisit(): Boolean {
       return this == (1 shl problemCnt) - 1
    }

    fun Int.isVisited(idx:Int): Boolean {
        return this and (1 shl idx) != 0
    }

    var min = Int.MAX_VALUE
    for (masking in 0 until (1 shl studentCnt)) {
        val filtered = students.filterIndexed { index, _ -> masking.isVisited(index) }
        var set = 0
        filtered.forEach { list ->
            list.forEach {
                set = set.visit(it - 1)
            }
        }

        if (set.allVisit()) {
            val count = filtered.size
            min = min.coerceAtMost(count)
        }
    }

    if (min == Int.MAX_VALUE)
        min = -1

    println(min)
}