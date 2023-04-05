import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val (size, max) = readLine().split(" ").map { it.toInt() }

    data class Point(val x:Int, val y:Int)

    val house = mutableListOf<Point>()
    val chickens = mutableListOf<Point>()

    repeat(size) { i ->
        readLine().split(" ").map { it.toInt() }.forEachIndexed { j, num ->
            when (num) {
                1 -> {
                    house.add(Point(j, i))
                }
                2 -> {
                    chickens.add(Point(j, i))
                }
            }
        }
    }

    val combinations = (1 until (1 shl chickens.size)).map { masking ->
        chickens.filterIndexed { index, _ -> masking and (1 shl index) != 0}
    }.filter { it.size == max }

    val min = combinations.minOf { selectedChickens ->
        house.sumOf { a ->
            selectedChickens.minOf { b ->
                abs(a.x - b.x) + abs(a.y - b.y)
            }
        }
    }

    print(min)
}