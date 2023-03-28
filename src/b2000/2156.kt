import kotlin.system.exitProcess

/*
 DP
 연속되지 않도록 점화 식을 설계
 */
fun main() {
    val n = readln().toInt()
    val list = List(n) {
        readln().toInt()
    }

    val memo = IntArray(list.size) { 0 }

    memo[0] = list[0]
    if (n > 1)
        memo[1] = memo[0] + list[1]
    if (n > 2)
        memo[2] = listOf(memo[1], memo[0] + list[2], list[1] + list[2]).max()

    for (i in 3 .. memo.lastIndex) {
        memo[i] = listOf(memo[i - 1], memo[i - 2] + list[i], memo[i - 3] + list[i] + list[i - 1]).max()
    }

    println(memo.max())
}