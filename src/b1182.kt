private val out = System.out.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(" ").map{ it.toInt() }
    data = readLine().split(" ").map { it.toInt() }.toIntArray()
    visit = BooleanArray(n){false}

    subSet(0, n, s)
    println(count)
}

private lateinit var data: IntArray
private lateinit var visit: BooleanArray
private var count = 0
private fun subSet(depth: Int, n: Int, s:Int) {
    if (depth == n) {
        var sum = 0
        repeat(n) {
            if (visit[it])
                sum += data[it]
        }
        if (sum == s && !visit.all { !it }) {
            count++
        }
        return
    }

    visit[depth] = true
    subSet(depth + 1, n, s)
    visit[depth] = false
    subSet(depth + 1, n, s)
}