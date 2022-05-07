package test.kakoTest2205


fun main() {
    println(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)).contentDeepToString())
    println(
        solution(
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
            arrayOf("Rotate")
        ).contentDeepToString()
    )
    println(
        solution(
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
            arrayOf("Rotate", "Rotate")
        ).contentDeepToString()
    )
    println(
        solution(
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
            arrayOf("Rotate", "Rotate", "Rotate")
        ).contentDeepToString()
    )
    println(
        solution(
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
            arrayOf("Rotate", "Rotate", "Rotate", "Rotate" )
        ).contentDeepToString()
    )
}

fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
    val arr = Array(rc.size) { i ->
        Array(rc[i].size) { j ->
            intArrayOf(i, j)
        }
    }

    operations.forEach {
        when(it) {
            "Rotate" -> {
                rotate(arr)
            }
            "ShiftRow" -> {
                shift(arr)
            }
        }
    }

    val answer: Array<IntArray> = Array(rc.size) {
        IntArray(rc[it].size){0}
    }
    arr.forEachIndexed { i, row ->
        row.forEachIndexed { j, idx ->
            answer[idx[0]][idx[1]] = rc[i][j]
        }
    }

    return answer
}

fun shift(arr: Array<Array<IntArray>>) {
    val size = arr.size
    arr.forEach { row ->
        row.forEach { idx ->
            idx[0] = (idx[0] + 1) % size
        }
    }
}

fun rotate(arr: Array<Array<IntArray>>) {
    val rowSize = arr.size
    val columnSize = arr.first().size

    // 첫 행 조건
    val first = arr.first()
    for(i in 0 until columnSize - 1) {
        val now = first[i];
        now[1] = (now[1] + 1) % columnSize
    }
    // 끝 열 조건
    for (i in 0 until rowSize - 1) {
        val now = arr[i].last()
        now[0] = (now[0] + 1) % rowSize
    }

    // 끝 행 조건
    val last = arr.last()
    for(i in 1 until columnSize) {
        val now = last[i];
        now[1] = (now[1] + 2) % columnSize
    }
    // 첫 열 조건
    for (i in 1 until rowSize) {
        val now = arr[i].first()
        now[0] = (now[0] + 2) % rowSize
    }
}