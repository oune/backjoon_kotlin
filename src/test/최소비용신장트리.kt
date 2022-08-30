package test.test

fun main() = with(System.`in`.bufferedReader()) {
    val (v, e) = readLine().split(" ").map { it.toInt() }

    val vertex = IntArray(v + 1) { it }
    fun findSet(idx:Int) :Int{
        if (idx != vertex[idx])
            vertex[idx] = findSet(vertex[idx])

        return vertex[idx]
    }

    fun unionSet(from:Int, to:Int) {
        vertex[findSet(to)] = findSet(from)
    }

    fun isUnion(from:Int, to:Int) :Boolean{
        return findSet(from) == findSet(to)
    }


    val lines = Array(e) {
        val (a, b, price) = readLine().split(" ").map { it.toInt() }

        data class State(val a:Int, val b:Int, val price:Int)

        State(a, b, price)
    }.sortedBy { it.price }

    var sum = 0
    lines.forEach {
        val (a, b, price) = it

        if (!isUnion(a, b)) {
            unionSet(a, b)
            sum += price
        }
    }

    println(sum)
}