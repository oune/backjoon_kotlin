import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    data class Food(val sour:Int, val bitter: Int)

    val foodsCnt = readLine().toInt()
    val foods = List(foodsCnt) {
        val (sour, bitter) = readLine().split(" ").map { it.toInt() }
        Food(sour, bitter)
    }

    fun List<Food>.subset(): List<List<Food>> {
        val res = mutableListOf<List<Food>>()

        for (i in 0 until (1 shl this.size)) {
            val list = mutableListOf<Food>()
            for (j in this.indices) {
                if (i and (1 shl j) != 0) {
                    list.add(this[j])
                }
            }
            res.add(list)
        }

        return res
    }

    val min = foods.subset().filter { it.isNotEmpty() }.minOf { list ->
        val bitter = list.sumOf { it.bitter }
        val sour = list.fold(1) { acc, food ->
            acc * food.sour
        }
        abs(bitter - sour)
    }

    println(min)
}