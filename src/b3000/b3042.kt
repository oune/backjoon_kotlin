package test.b3000

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val arr = Array(size) {
        readLine().toCharArray()
    }

    data class Point(val x:Int, val y:Int)
    val points = mutableListOf<Point>()
    arr.forEachIndexed { i, line ->
        line.forEachIndexed { j, char ->
            if (char != '.') {
                points.add(Point(j, i))
            }
        }
    }

    fun isSameLine(a: Point, b:Point, c:Point) : Boolean{
        return (a.x - c.x) * (a.y - b.y) == (a.y - c.y) * (a.x - b.x)
    }

    var count = 0
    for (i in 0..points.lastIndex)
        for (j in i + 1 .. points.lastIndex)
            for (k in j + 1 .. points.lastIndex)
                if (isSameLine(points[i], points[j], points[k]))
                    count++

    println(count)
}