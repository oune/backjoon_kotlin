import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val a = '_' + readLine()
    val b = '_' + readLine()
    val ans = List(a.length) { IntArray(b.length){ 0 } }
    var max = 0
    for (i in 1 until a.length) {
        for (j in 1 until b.length) {
            if (a[i] == b[j]) {
                ans[i][j] = ans[i - 1][j - 1] + 1
                if (max < ans[i][j]) {
                    max = ans[i][j]
                }
            } else {
                ans[i][j] = max(ans[i - 1][j], ans[i][j - 1])
            }
        }
    }
    println(max)
    if (max != 0) {
        var maxAns = ""
        var idxA = a.length - 1
        var idxB = b.length - 1
        while(idxA != 0 && idxB != 0) {
            if (ans[idxA - 1][idxB] == ans[idxA][idxB]) {
                idxA--
            } else if (ans[idxA][idxB - 1] == ans[idxA][idxB]) {
                idxB--
            } else if (a[idxA] == b[idxB]) {
                maxAns = a[idxA] + maxAns
                idxA--
                idxB--
            } else {
                idxA--
                idxB--
            }
        }

        println(maxAns)
    }
}
