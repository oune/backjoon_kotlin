package test.test

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val set = IntArray(n + 1) { it }
    fun findSet(idx:Int) :Int{
        if (set[idx] != idx)
            set[idx] = findSet(set[idx])

        return set[idx]
    }

    fun unionSet(from:Int, to:Int){
        set[findSet(from)] = findSet(to)
    }

    val out = System.out.bufferedWriter()
    repeat(m) {
        val (operator, a, b) = readLine().split(" ").map { it.toInt() }

        when(operator) {
            0 -> { unionSet(a, b)
            }
            1 -> { out.appendLine(if (findSet(a) == findSet(b)) "YES" else "NO")
            }
        }
    }
    out.flush()
}