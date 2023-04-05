import java.lang.Math.abs
import kotlin.math.ceil

fun main() = with(System.`in`.bufferedReader()) {
    val peopleCnt = readLine().toInt()
    val people = List(peopleCnt) { it }

    val stats = List(peopleCnt) {
        readLine().split(" ").map { it.toInt() }
    }

    fun List<Int>.subset(): List<Pair<List<Int>, List<Int>>> {
        fun Int.isVisited(index:Int): Boolean {
            return this and (1 shl index) != 0
        }

        val res = mutableListOf<Pair<List<Int>, List<Int>>>()

        for (masking in 1 until  (1 shl this.size) ) {
            val list1 = this.filterIndexed { index, _ -> masking.isVisited(index)}
            val list2 = this.filterIndexed { index, _ -> !masking.isVisited(index)}
            res.add(Pair(list1, list2))
        }

        return res
    }

    val subsets = people.subset().filter { it.first.size <= people.size / 2}

    val ans = subsets.map { (left, right) ->
        var teamLeft = 0
        left.forEach { from ->
            left.forEach { to ->
                teamLeft += stats[from][to]
            }
        }

        var teamRight = 0
        right.forEach { from ->
            right.forEach { to ->
                teamRight += stats[from][to]
            }
        }

        val distance = kotlin.math.abs(teamLeft - teamRight)

        distance
    }.min()

    println(ans)
}