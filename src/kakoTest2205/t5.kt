package test.kakoTest2205


fun main() {
//    println(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)).contentDeepToString())
//    println(
//        solution(
//            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
//            arrayOf("Rotate")
//        ).contentDeepToString()
//    )
//    println(
//        solution(
//            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
//            arrayOf("Rotate", "Rotate")
//        ).contentDeepToString()
//    )
//    println(
//        solution(
//            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
//            arrayOf("Rotate", "Rotate", "Rotate")
//        ).contentDeepToString()
//    )
//    println(
//        solution(
//            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
//            arrayOf("Rotate", "Rotate", "Rotate", "Rotate" )
//        ).contentDeepToString()
//    )
//    println("===============================")
    var rc = arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12))
    var operations = arrayOf("ShiftRow", "Rotate", "ShiftRow", "Rotate")
    println(solution(rc, operations).contentDeepToString())
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
    val rowLast = arr.lastIndex
    val columnSize = arr.first().size
    val columnLast = arr.first().lastIndex

    arr.forEach { row ->
        row.forEach { idx ->
            if (idx[0] == 0 && idx[1] != columnLast) { // 첫행
                idx[1] = (idx[1] + 1) % columnSize
            } else if (idx[1] == columnLast && idx[0] != rowLast) { // 끝열
                idx[0] = (idx[0] + 1) % rowSize
            } else if (idx[0] == rowLast && idx[1] != 0) { // 끝행
                idx[1] = (idx[1] + columnSize -1) % columnSize
            } else if (idx[1] == 0 && idx[0] != 0) { // 첫 열
                idx[0] = (idx[0] + rowSize - 1) % rowSize
            }
        }
    }
}