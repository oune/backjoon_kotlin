/*
서로소 집합
어느곳에 있어도 safe zone 에 도착할 수 있도록 하는 최소의 safe zone 의 개수
연결 되어있는 선의 마지막에 safe 존을 놓는 다면 최소의 개수로 연결 할 수 있음
또한 연결이 두가닥으로 나뉘는 경우가 존재 하지 않음.

30분 소요
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (height, width) = readLine().split(" ").map { it.toInt() }

    val parents = List(height) { i ->
        Array(width) { j -> Pair(j, i) }
    }

    fun findSet(idx:Pair<Int, Int>): Pair<Int, Int> {
        val (x, y) = idx
        if (parents[y][x] != idx)
            parents[y][x] = findSet(parents[y][x])

        return parents[y][x]
    }

    fun unionSet(a:Pair<Int, Int>, b:Pair<Int, Int>) {
        val pa = findSet(a)
        val pb = findSet(b)
        parents[pa.second][pa.first] = pb
    }

    fun isUnion(a:Pair<Int, Int>, b:Pair<Int, Int>): Boolean {
        return findSet(a) == findSet(b)
    }


    val map = List(height) { readLine().toList() }

    for (i in map.indices) {
        for (j in map[i].indices) {
            val moved = when(map[i][j]) {
                'U' -> { Pair(j, i - 1) }
                'D' -> { Pair(j, i + 1) }
                'L' -> { Pair(j - 1, i) }
                'R' -> { Pair(j + 1, i) }
                else -> { Pair(-1, -1) }
            }

            if (moved.second in map.indices && moved.first in map[moved.second].indices) {
                unionSet(Pair(j, i), moved)
            }
        }
    }

    val count = parents.flatMap { line ->
        line.map { findSet(it) }.distinct()
    }.distinct()

    println(count.size)
}

