fun main() = with(System.`in`.bufferedReader()) {
    val groundCnt = readLine().toInt()
    val county = readLine().split(" ").map { it.toInt() }
    val graph = Array(groundCnt) {
        readLine().split(" ").drop(1).map { it.toInt() - 1}
    }
    visit = BooleanArray(groundCnt)
    subset(0, groundCnt)
}
private lateinit var visit :BooleanArray
fun subset(depth:Int, n:Int) {
    if (depth == n) {
        for (i in 0 until n) {
            if (visit[i]) {
                print("$i ")
            }
        }
        println()
        return
    }

    visit[depth] = false
    subset(depth + 1, n)
    visit[depth] = true
    subset(depth + 1, n)
}