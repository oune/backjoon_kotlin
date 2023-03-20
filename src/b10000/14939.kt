/*
그리디
아이디어가 중요
위에 불 이 켜진게 존재 하면 스위치를 조작함
 */

fun main() = with(System.`in`.bufferedReader()) {
    val origin = List(10) {
        readLine().map { it != '#' }.toBooleanArray()
    }

    val moves = listOf(
        {x:Int, y:Int -> Pair(x, y - 1)},
        {x:Int, y:Int -> Pair(x + 1, y)},
        {x:Int, y:Int -> Pair(x, y) },
        {x:Int, y:Int -> Pair(x - 1, y)},
        {x:Int, y:Int -> Pair(x, y + 1)},
    )

    fun List<BooleanArray>.click(x:Int, y:Int) {
        for (move in moves) {
            val (nx, ny) = move(x, y)

            if (ny !in this.indices || nx !in this[ny].indices)
                continue

            this[ny][nx] = !this[ny][nx]
        }
    }

    var min = Int.MAX_VALUE
    for (masking in 0 until (1 shl origin.size)) {
        val map = List(origin.size) {
            origin[it].clone()
        }

        var count = 0
        for (idx in map[0].indices) {
            if (masking and (1 shl idx) == 0)
                continue

            count++
            map.click(idx, 0)
        }

        for (i in 1  .. map.lastIndex) {
            for (j in map[i].indices) {
                if (!map[i - 1][j])
                    continue

                count++
                map.click(j, i)
            }
        }

        val isClear = map.all { line -> line.all { !it } }
        if (isClear) {
            min = min.coerceAtMost(count)
        }
    }

    println(if (min != Int.MAX_VALUE) min else - 1)
}