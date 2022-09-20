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

    println(arr.contentDeepToString())

    val sumArr = arr.map { line ->
        var pre = 0
        line.map {
            val num = pre
            pre = it
            if (it == 0) 0 else it + num
        }
    }

    println(sumArr)

    var max = 0
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (sumArr[i][j] == 0)
                continue

            loop@for (endJ in j..arr[i].lastIndex) {
                var sum = 0
                for (endI in i..arr.lastIndex) {
                    if (sumArr[endI][endJ] == 0)
                        break@loop

                    sum += sumArr[endI][endJ]

                    if (sum > max)
                        max = sum
                }
            }
        }
    }

    println(max)
}