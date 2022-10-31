package b2000

fun main() = with(System.`in`.bufferedReader()) {
    val totalCnt = readLine().toInt()
    val students = Array(3) {
        readLine().split(' ').filter { it != "" }.map { it.toInt() }.toIntArray()
    }
    val res = Array(3) { IntArray(3) { 0 } }

    for (i in students.indices) {
        for (j in students.indices) {
            val boys = students[i][0]
            val otherGirls = students[j][1]
            if (i != j) {
                if (boys <= otherGirls) {
                    students[i][0] = 0
                    students[j][1] = otherGirls - boys
                    res[i][j] += boys
                } else {
                    students[i][0] = boys - otherGirls
                    students[j][1] = 0
                    res[i][j] += otherGirls
                }
            }
        }
    }

    val out = System.out.bufferedWriter()
    if (res.sumOf { it.sum() } == totalCnt) {
        out.appendLine("1")
        res.forEachIndexed { i, arr ->
            arr.forEachIndexed { j, num ->
                if (i != j) {
                    out.append("$num ")
                }
            }
            out.newLine()
        }
    } else
        out.appendLine("0")


    out.flush()
}