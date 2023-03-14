import java.util.LinkedList

/*
위상정렬
선행관계를 가지는 것의 최소의 시간을 구하라
시간을 늘려가면서 확인하는 방식으로 구현했는데 더 나은 방식을 찾아야 겠음.

틀린 이유 (4%)
같은 깊이를 가지는 비용들중 최대값들의 합이 최소의 시간 으로 구현했을때
반례
3
1 0
1 1 1
5 2 1 2
정답 : 7
결과 : 6
건너 뛰어서 방문 했을 경우 동시가 아니지만, 동시로 취급이 됨

걸린 시간 1시간 반
반례 잘못 만들어서 더 오래 걸렸음.

 */
fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val map = List(size + 1) {
        LinkedList<Int>()
    }

    val indegree = IntArray(map.size) { 0 }
    indegree[0] = -1
    val costs = IntArray(map.size) { 0 }

    repeat(size) { i ->
        val idx = i + 1
        val input = readLine().split(" ").map { it.toInt() }
        costs[idx] = input[0]

        for (next in input.drop(2)) {
            map[next].add(idx)
            indegree[idx]++
        }
    }

    var list = mutableListOf<Int>()
    for (i in indegree.indices) {
        if (indegree[i] == 0) {
            list += i
        }
    }

    var time = 0
    while(!costs.all { it == 0 }) {
        time++

        for (now in list) {
            costs[now]--
        }

        list = list.flatMap { now ->
            if (costs[now] == 0) {
                map[now].filter { --indegree[it] == 0 }
            }
            else
                listOf(now)
        }.toMutableList()
    }

    println(time)
}