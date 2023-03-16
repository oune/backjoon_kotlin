/*
이분 탐색
9분
 */
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val have = readLine().split(" ").map { it.toInt() }.sorted()
    val m = readLine().toInt()
    val problems = readLine().split(" ").map { it.toInt() }



    val ans = mutableListOf<Int>()
    for (problem in problems) {
        val idx = have.binarySearch(problem)
        println(idx)
        ans.add(if (idx != -1) 1 else 0)
    }

    println(ans.joinToString(" "))
}