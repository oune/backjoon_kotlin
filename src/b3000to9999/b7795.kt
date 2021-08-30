package b3000to9999

fun main() = with(System.`in`.bufferedReader()) {
    val testCnt = readLine().toInt()
    repeat(testCnt) {
        readLine()
        val an = readLine().split(" ").map { it.toInt() }.sorted() + 0
        val bn = readLine().split(" ").map { it.toInt() }.sorted() + Int.MAX_VALUE

        println(an)
        println(bn)

        var idxA = 0
        var idxB = 0
        var count = 0
        while (idxA < an.size) {
            if (an[idxA] > bn[idxB]) {
                idxB++
                count++
            }
        }
    }
}