package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (kingPos, stonePos, moveCnt) = readLine().split(' ')

    data class Point(val x:Int, val y:Int)

    fun toPoint(pos:String) :Point {
        val x = pos[0].code - 'A'.code
        val y = pos[1].digitToInt() - 1
        return Point(x, y)
    }

    var king = toPoint(kingPos)
    var stone = toPoint(stonePos)

    repeat(moveCnt.toInt()) {
        var moveX = 0
        var moveY = 0
        readLine().forEach {
            when(it) {
                'R' -> {moveX++}
                'L' -> {moveX--}
                'B' -> {moveY--}
                'T' -> {moveY++}
                else -> {}
            }
        }

        val movedX = king.x + moveX
        val movedY = king.y + moveY

        if (movedX in 0..7 && movedY in 0..7) {
            if (movedX == stone.x && movedY == stone.y) {
                val movedStoneX = stone.x + moveX
                val movedStoneY = stone.y + moveY

                if (movedStoneX in 0..7 && movedStoneY in 0..7) {
                    stone = Point(movedStoneX, movedStoneY)
                    king = Point(movedX, movedY)
                }
            } else {
                king = Point(movedX, movedY)
            }
        }
    }

    fun Point.toStr() :String {
        return "" + (x + 'A'.code).toChar() + (y + 1)
    }

    println(king.toStr())
    println(stone.toStr())
}