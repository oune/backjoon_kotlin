import java.lang.RuntimeException

fun main() = with(System.`in`.bufferedReader()) {
    val (height, width) = readLine().split(" ").map { it.toInt() }
    val map = Array(height) {
        readLine().toCharArray()
    }

    data class Ball(val x:Int, val y:Int)

    fun Ball.left(other: Ball, iterator: Iterable<Int>): Ball {
        for (i in iterator) {
            when (map [this.y][i]) {
                '#' -> return Ball(i + 1, this.y)
                'O' -> return Ball(-1, -1)
            }

            if (this == other)
                return Ball(i + 1, this.y)
        }
        throw RuntimeException("out of bounds")
    }

    var a = Ball(1, 2)
    a.left(a, 0.. 10)

    repeat(10) {

    }
}