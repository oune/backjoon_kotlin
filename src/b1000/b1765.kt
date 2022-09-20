package b1000

fun main() = with(System.`in`.bufferedReader()) {
    val studentCount = readLine().toInt()

    val friends = IntArray(studentCount + 1) { it }
    val enemies = Array(friends.size) {
        mutableListOf<Int>()
    }

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
                enemies[a].add(b)
                enemies[b].add(a)
            }
            "F" -> {
                if (isNotUnion(friends, a, b))
                    unionSet(friends, a, b)
            }
        }
    }

    enemies.forEachIndexed { student, enemyEnemy ->
        enemyEnemy.forEach { enemyList ->
            enemies[enemyList].forEach { enemy ->
                if (isNotUnion(friends, student, enemy))
                    unionSet(friends, student, enemy)
            }
        }
    }

    val count = (1..studentCount).map { findSet(friends, it) }.toSet().size
    println(count)
}