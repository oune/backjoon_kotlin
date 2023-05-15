/*
* 위상정렬
* 가장 빨리 건설되는 건물의 시간을 이용해서 다른 건물의 잔여시간을 소진시키면서 병렬적으로 건설
* */
fun main() {
    val testcaseCnt = readln().toInt()
    val res = List(testcaseCnt) {
        val (n, k) = readln().split(" ").map { it.toInt() }

        val map = List(n + 1) { mutableListOf<Int>() }
        val inDegree = IntArray(n + 1) { 0 }

        val times = (listOf(0)  + readln().split(" ").map { it.toInt() }).toIntArray()

        repeat(k) {
            val (from, to) = readln().split(" ").map { it.toInt() }
            map[from].add(to)
            inDegree[to]++
        }

        var makings = (1 .. inDegree.lastIndex).filter { inDegree[it] == 0 }

        var time = 0
        val target = readln().toInt()
        while(times[target] != 0) {
            val min = makings.minOf { times[it] }

            time += min

            val addIdx = mutableListOf<Int>()
            val removeIdx = mutableListOf<Int>()
            for (i in makings) {
                times[i] -= min
                if (times[i] == 0) {
                    for (next in map[i]) {
                        inDegree[next]--

                        if (inDegree[next] == 0)
                            addIdx.add(next)
                    }
                    removeIdx.add(i)
                }
            }

            makings = (makings.filter { it !in removeIdx } + addIdx)
        }
        time
    }

    println(res.joinToString("\n"))
}