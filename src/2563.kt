fun main() = with(System.`in`.bufferedReader()) {
    val paperCnt = readLine().toInt()

    val map = Array(101) {
        BooleanArray(101) {
            false
        }
    }
    repeat(paperCnt) {
        val (x, y) = readLine().split(" ").map { it.toInt() }

        for (i in y until y + 10) {
            for (j in x until  x + 10) {
                map[i][j] = true
            }
        }
    }

    print(map.sumOf { list-> list.count { it } })
}