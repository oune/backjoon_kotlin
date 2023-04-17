import java.util.LinkedList

/*
위상정렬
부모로 부터 자식이 나오는 상황에서
정렬후 누가 가장 가깝게 위치하는지 확인
 */
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val start = readln()

    val map = HashMap<String, List<String>>()
    val indegrees = HashMap<String, Int>()
    val bloods = HashMap<String, Double>()

    repeat(n) {
        val (child, parentA, parentB) = readln().split(" ")

        indegrees[parentA] = indegrees[parentA] ?: 0
        indegrees[parentB] = indegrees[parentB] ?: 0
        indegrees[child] = indegrees[child]?.plus(2) ?: 2
        map[parentA] = (map[parentA] ?: listOf()) + listOf(child)
        map[parentB] = (map[parentB] ?: listOf()) + listOf(child)
    }

    val que = LinkedList<String>()
    for ((name, _) in indegrees.filter { (_, indegree) -> indegree == 0 }) {
        que.add(name)
        bloods[name] = if (name == start) 1.0 else 0.0
    }

    while (que.isNotEmpty()) {
        val now = que.poll()
        val half = bloods[now]!! / 2.0

        for (next in map[now]?: listOf()) {
            bloods[next] = bloods[next]?.plus(half) ?: half
            indegrees[next] = indegrees[next]!!.minus(1)
            if (indegrees[next] == 0)
                que.add(next)
        }
    }

    val persons = List(m) {
        val person = readln()
        Pair(person, bloods[person] ?: 0.0)
    }

    val king = persons.maxByOrNull { it.second }!!
    println(king.first)
}