package b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map{ it.toInt() }
    val unionSet = UnionSet(n + 1)

    repeat(m) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        if (a == 0) {
            unionSet.unionSet(b, c)
        } else if (a == 1){
            if (unionSet.findSet(b) == unionSet.findSet(c)) {
                println("YES")
            } else {
                println("NO")
            }
        }
    }
}

private class UnionSet (size : Int){
    val data: Array<Int> = Array(size) { it }

    fun findSet(p :Int): Int {
        if (p != data[p])
            data[p] = findSet(data[p])
        return data[p]
    }

    fun unionSet(a:Int, b:Int) {
        val ap = findSet(a)
        val bp = findSet(b)
        data[ap] = bp
    }
}