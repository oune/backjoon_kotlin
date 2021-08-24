package test

fun main() {
    val (points, lines) = readLine()!!.split(" ").map { it.toInt() }

    val list = Array<MutableList<Int>>(points) { mutableListOf() }

    repeat(lines) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        list[a] += b
        list[b] += a
    }
    list.forEach {
        it.sort()
    }

    print(list.contentDeepToString())
}