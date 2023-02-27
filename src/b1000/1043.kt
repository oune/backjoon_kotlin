fun main() = with(System.`in`.bufferedReader()) {
    class Set(size: Int) {
        val parent = IntArray(size) { it }
        fun findSet(num:Int):Int {
            if (num != parent[num]) {
                parent[num] = findSet(parent[num])
            }
            return parent[num]
        }
        fun union(a:Int, b:Int) {
            val pa = findSet(a)
            val pb = findSet(b)
            parent[pa] = pb
        }
    }

    val (peopleCnt, partyCnt) = readLine().split(" ").map { it.toInt() }

    val knowns = readLine().split(" ").drop(1).map { it.toInt() }

    val party = List(partyCnt) {
        readLine().split(" ").drop(1).map { it.toInt() }
    }

    val set = Set(peopleCnt + 1)

    val known = if (knowns.isEmpty()) 0 else knowns.first()
    for (person in knowns.drop(1)) {
        set.union(person, known)
    }

    for (people in party) {
        val target = people.first()
        for (person in people.drop(1)) {
            set.union(target, person)
        }
    }

    val count = party.count { people ->
        people.all {
            set.findSet(it) != set.findSet(known)
        }
    }

    print(count)
}