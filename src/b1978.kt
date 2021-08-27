import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val nums = readLine().split(" ").map{ it.toInt() }
    val size = 1001
    val che = Array(size) {true}
    che[0] = false
    che[1] = false
    for (i in (2..sqrt(size.toDouble()).toInt())) {
        if (che[i]) {
            var idx = i * i
            while (idx < size) {
                che[idx] = false
                idx += i
            }
        }
    }
    print(nums.count{ che[it] })
}