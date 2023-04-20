/*최소신장트리
* 트리를 구성할때 가장 비용이 적을 때와 가장 비용이 높을 때 두가지로 나뉘고, 그의 차이를 구해야함.
* 따라서 오름차순으로 mst를 계산한 것과, 내림차순으로 mst를 계산한 것의 차이를 구함
* 피로도 = 오르막길의 수 ^ 2
* kruskal 알고리즘의 시간 복잡도는 O(e log e)
* prim의 시간복잡도는 O(e log v) */

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    data class State(val from:Int, val to:Int, val cost:Int)
    val edges = List(m + 1) {
        val (from, to, cost) = readln().split(" ").map { it.toInt() }
        State(from, to, if (cost == 0) 1 else 0)
    }


    fun List<State>.getCount(): Int {
        var min = 0
        val set = DisjointSet(n + 1)

        for ((from, to, cost) in this) {
            if (!set.isUnion(from, to)) {
                set.unionSet(from, to)
                min += cost
            }
        }

        return min * min
    }

    val min = edges.sortedBy { it.cost }.getCount()
    val max = edges.sortedBy { -it.cost }.getCount()

    println(max - min)
}

class DisjointSet(size:Int) {
    val parents = IntArray(size) { it }
    fun findSet(idx:Int):Int {
        if (parents[idx] != idx)
            parents[idx] = findSet(parents[idx])
        return parents[idx]
    }

    fun unionSet(a:Int, b:Int) {
        val pa = findSet(a)
        val pb = findSet(b)
        parents[pa] = pb
    }

    fun isUnion(a:Int, b:Int) :Boolean {
        return findSet(a) == findSet(b)
    }
}