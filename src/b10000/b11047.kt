package b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val arr = IntArray(n) {
        readLine().toInt()
    }

    var sum = 0
    var cnt = 0
    run {
        arr.reversed().forEach {
            while (sum + it <= k) {
                sum += it
                cnt++

                if (sum == k)
                    return@run
            }
        }
    }
    print(cnt)
}
