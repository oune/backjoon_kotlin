fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val balls = readLine().split(" ").map { it.toInt() }
    dfs(balls, 0)
    print(max)
}
private var max = 0
fun dfs (balls: List<Int>, acc:Int) {
    if (max < acc)
        max = acc

    for (i in 1 until balls.size -1) {
        val clone = balls.toMutableList()
        clone.removeAt(i)
        dfs(clone, acc + balls[i - 1] * balls[i + 1])
    }
}