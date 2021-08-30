fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map{ it.toInt() }
    val arr = Array(n) {it}

    var res = 0
    for (i in 0 until m) {
        val turn = i + 1
        val (a, b) = readLine().split(" ").map { it.toInt() }

        if (findSet(arr, a) != findSet(arr, b)) {
            union(arr, a, b)
        } else {
            res = turn
            break;
        }
    }
    print(res)
}

fun findSet(arr:Array<Int>, p:Int):Int {
    if (p != arr[p])
        arr[p] = findSet(arr, arr[p])
    return arr[p]
}

fun union(arr:Array<Int>, a:Int, b:Int) {
    val ap = findSet(arr, a)
    val bp = findSet(arr, b)
    arr[ap] = bp
}