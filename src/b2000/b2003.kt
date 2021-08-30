fun main() = with(System.`in`.bufferedReader()) {
    val (_, m) = readLine().split(" ").map { it.toInt() }
    val nums = readLine().split(" ").map { it.toInt() } + 0

    var left = 0
    var right = 0
    var sum = 0
    var count = 0
    while(right < nums.size) {
        if (sum == m)
            count++
        if (sum > m) {
            sum -= nums[left++]
        } else {
            sum += nums[right++]
        }
    }
    print(count)
}