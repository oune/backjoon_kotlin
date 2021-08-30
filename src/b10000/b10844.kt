fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()
    val arr = List(num) { MutableList(10 + 2) { 0L } }

    for (i in 2..10) {
        arr[0][i] = 1L
    }
    for (i in 1 until num) {
        for (j in 1..10) {
            arr[i][j] = arr[i-1][j-1] + arr[i-1][j+1]
            arr[i][j] %= 1000000000L
        }
    }
    var sum = 0L
    for (n in arr.last()) {
        sum += n
        sum %= 1000000000L
    }
    print(sum)
}