package test.test

fun main() {
    val nodeCnt = 6

    val move = Array(nodeCnt + 1) {
        mutableListOf<Int>()
    }

    val lineCnt = readLine()!!.toInt()
    repeat(lineCnt) {
        val (from, to) = readLine()!!.split(" ").map { it.toInt() }
        move[from].add(to)
        move[to].add(from)
    }

    move.forEachIndexed() { idx, m ->
        print("$idx: ")
        m.forEach {
            print("$it ")
        }
        println()
    }
}