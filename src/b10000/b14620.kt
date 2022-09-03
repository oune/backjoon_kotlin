package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val arr = Array(size) {
        readLine().split(' ').map { it.toInt() }
    }

    data class Point(val x:Int, val y:Int)

    var visited = Array(size) {
        BooleanArray(size) { false }
    }

    fun sum(point:Point) :Int{
        var sum = 0
        listOf(
            {from:Point -> from},
            {from:Point -> Point(from.x + 1, from.y)},
            {from:Point -> Point(from.x - 1, from.y)},
            {from:Point -> Point(from.x, from.y + 1)},
            {from:Point -> Point(from.x, from.y - 1)},
        ).forEach {
            val moved = it(point)

            sum += if (moved.y in arr.indices && moved.x in arr[moved.y].indices && !visited[moved.y][moved.x]) {
                visited[moved.y][moved.x] = true
                arr[moved.y][moved.x]
            } else {
                200 * 5 * 4
            }
        }
        return sum
    }

    var min = 200 * 5 * 4
    for (i1 in 0 .. arr.lastIndex) {
        for (j1 in 0 .. arr[i1].lastIndex) {
            for (i2 in 0 .. arr.lastIndex) {
                for (j2 in 0 .. arr[i2].lastIndex) {
                    for (i3 in 0 .. arr.lastIndex) {
                        for (j3 in 0 .. arr[i3].lastIndex) {
                            visited = Array(size) { BooleanArray(size) { false } }

                            val sum = sum(Point(j1, i1)) + sum(Point(j2, i2)) + sum(Point(j3, i3))
                            if (sum < min)
                                min = sum
                        }
                    }
                }
            }
        }
    }

    println(min)
}