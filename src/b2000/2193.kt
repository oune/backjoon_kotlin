/*
DP
 */
fun main() {
    val n = readln().toInt()
    val arr = LongArray(n + 1) { 1 }

    for (i in 3..n) {
        arr[i] = arr[i - 1] + arr[i - 2]
    }

    print(arr.last())
}