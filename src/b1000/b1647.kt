package b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    data = IntArray(n + 1){it}

    var sum = 0
    val arr = Array(m) {
        readLine().split(" ").map { it.toInt() }
    }.sortedBy{
        it[2]
    }

    var last = 0;
    for (now in arr) {
        if (findSet(now[0]) != findSet(now[1])) {
            union(now[0], now[1])
            sum += now[2]
            last = now[2]
        }
    }
    print(sum - last)
}

private lateinit var data:IntArray
private fun findSet(p :Int): Int{
    if (p != data[p])
        data[p] = findSet(data[p])
    return data[p]
}

private fun union(a:Int, b:Int) {
    val ap = findSet(a)
    val bp = findSet(b)
    data[ap] = bp
}