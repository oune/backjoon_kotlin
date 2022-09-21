package b2000

fun main() = with(System.`in`.bufferedReader()) {
    val vertical = mutableListOf<Int>()
    val horizontal = mutableListOf<Int>()

    val blacks = Array(readLine().toInt()) {
        val (x, y) = readLine().split(' ').map { it.toInt() }

        horizontal.add(x)
        horizontal.add(x + 10)
        vertical.add(y)
        vertical.add(y + 10)
        Pair(x, y)
    }

    val rows = vertical.toSet().sorted()
    val columns = horizontal.toSet().sorted()
    val arr = Array(rows.size - 1) {
        IntArray(columns.size - 1) { 0 }
    }

    blacks.forEach { (x, y) ->
        for (i in rows.indexOf(y) until rows.indexOf(y + 10)) {
            for (j in columns.indexOf(x) until columns.indexOf(x + 10)) {
                val lengthX = columns[j + 1] - columns[j]
                val lengthY = rows[i + 1] - rows[i]
                arr[i][j] = lengthX * lengthY
            }
        }
    }

    val sumArr = arr.map { line ->
        var pre = 0
        line.map {
            val ans = if (it == 0) 0 else it + pre
            pre = ans
            ans
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
                    val size = arr.slice(i..newI).sumOf {
                        it.slice(j..newJ).sum()
                    }
                    if (size > max)
                        max = size
                }
            }
        }
    }

    println(max)
}