import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader() ){
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val parents = IntArray(n) { it }
    fun findSet(idx:Int):Int {
        if (idx != parents[idx])
            parents[idx] = findSet(parents[idx])
        return parents[idx]
    }
    fun unionSet(a:Int, b:Int) {
        val pa = findSet(a)
        val pb = findSet(b)
        parents[pa] = pb
    }
    fun isUnion(a:Int, b:Int): Boolean {
        return findSet(a) == findSet(b)
    }

    var count = 0
    repeat(m) {
        val (from, to) = readLine().split(" ").map { it.toInt() -1 }
        if (!isUnion(from, to)) {
            unionSet(from, to)
            count++
        }
    }

    data class State(val from:Int, val to:Int, val cost:Int)
    val lines = PriorityQueue<State>( compareBy { it.cost })

    repeat(n) { start ->
        val costs = readLine().split(" ").map { it.toInt() }

        if (start != 0) {
            for (end in start + 1 .. costs.lastIndex)
                lines.add(State(start, end, costs[end]))
        }
    }

    var total = 0
    val newLines = mutableListOf<Pair<Int, Int>>()

    while (lines.isNotEmpty() && count < n - 2) {
        val (from, to, cost) = lines.poll()

        if (isUnion(from, to))
            continue

        total += cost
        unionSet(from, to)
        newLines.add(Pair(from + 1, to + 1))
        count++
    }

    println("$total ${newLines.size}")
    newLines.forEach { (first, second) ->
        println("$second $first")
    }
}