fun main() = with(System.`in`.bufferedReader()){
    val a = "_" + readLine()
    val b = "_" + readLine()
    val ans = List(a.length){ IntArray(b.length) { 0 } }
    var max = 0
    for (i in 1 until a.length) {
        for (j in 1 until b.length) {
            if (a[i] == b[j]) {
                ans[i][j] = ans[i - 1][j - 1] + 1
                if (max < ans[i][j])
                    max = ans[i][j]
            }
        }
    }
    print(max)
}