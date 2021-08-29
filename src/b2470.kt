import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val solutions = readLine().split(" ").map { it.toInt() }.sortedBy { abs(it) } + 0

    var left = 0
    var right = 1
    var ans = Triple(-1, -1, Int.MAX_VALUE)
    while (right < solutions.size) {
        val sum = solutions[left] + solutions[right]
        if (abs(ans.third) > abs(sum)){
            ans = Triple(solutions[left], solutions[right], sum)
        }
        if (sum > ans.third) {
            left++
        } else {
            right++
        }
    }
    print("${ans.first} ${ans.second}")
}
