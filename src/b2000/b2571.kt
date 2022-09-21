package b2000

fun main() = with(System.`in`.bufferedReader()) {
    val arrSize = 101
    val arr = Array(arrSize) { IntArray(arrSize) { 0 } }

    Array(readLine().toInt()) {
        val (x, y) = readLine().split(' ').map { it.toInt() }

        for (i in y until y + 10) {
            for (j in x until x + 10) {
                arr[i][j] = 1
            }
        }
    }

    var max = 0
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (arr[i][j] == 0)
                continue

            var lastIdx = arr[i].lastIndex
            for (newI in i..arr.lastIndex) {
                for (newJ in j..lastIdx) {
                    if (arr[newI][newJ] == 0) {
                        lastIdx = newJ - 1
                        break
                    }
                    // size check
                    val size = (newI - i + 1) * (newJ - j + 1)

                    if (size > max)
                        max = size
                }
            }
        }
    }

    println(max)
}