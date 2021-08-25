private lateinit var arr:Array<Int>

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    arr = Array(n + 1) {it}
    var sum = 0
    Array(m) {
        readLine().split(" ").map { it.toInt() }
    }.sortedBy { it[2] }.forEach {
        if (findSet(it[0]) != findSet(it[1])) {
            sum += it[2]
            union(it[0], it[1])
        }
    }

    print(sum)
}

fun findSet(p:Int):Int {
    if (p != arr[p])
        arr[p] = findSet(arr[p])
    return arr[p]
}

fun union(a:Int, b:Int) {
    val ap = findSet(a)
    val bp = findSet(b)
    arr[ap] = bp
}
