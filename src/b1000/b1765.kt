package b1000

fun main() = with(System.`in`.bufferedReader()) {
    val studentCount = readLine().toInt()

    val friends = IntArray(studentCount + 1) { it }
    val enemies = IntArray(studentCount + 1) { it }

    fun findSet(set:IntArray, vertex:Int):Int {
        if (vertex != set[vertex])
            set[vertex] = findSet(set, set[vertex])
        return set[vertex]
    }

    fun isUnion(set:IntArray, a:Int, b:Int):Boolean {
        return findSet(set, a) == findSet(set, b)
    }

    fun isNotUnion(set:IntArray, a:Int, b:Int):Boolean = !isUnion(set, a, b)

    fun unionSet(set:IntArray, a:Int, b:Int) {
        set[findSet(set, a)] = findSet(set, b)
    }

    repeat(readLine().toInt()) {
        val (type, first, second) = readLine().split(' ')
        val a = first.toInt()
        val b = second.toInt()

        when(type) {
            "E" -> {
                (1..studentCount).filter {
                    it != a
                }.filter {
                    it != b
                }.filter {
                    isUnion(enemies, b, it)
                }.filter {
                    isNotUnion(friends, a, it)
                }.forEach {
                    unionSet(friends, a, it)
                }

                (1..studentCount).filter {
                    it != a
                }.filter {
                    it != b
                }.filter {
                    isUnion(enemies, a, it)
                }.filter {
                    isNotUnion(friends, b, it)
                }.forEach {
                    unionSet(friends, b, it)
                }

                if (isNotUnion(enemies, a, b))
                    unionSet(enemies, a, b)
            }
            "F" -> {
                if (isNotUnion(friends, a, b))
                    unionSet(friends, a, b)
            }
        }
    }

    val set = (1..friends.lastIndex).map { findSet(friends, it) }.toSet()
    val count = set.size
    println(enemies.contentToString())
    println(friends.contentToString())
    println(set)
    println(count)
}