
fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()

    val numbs = List(10) { it }
    var count = 0
    var ans :String? = null
    for (size in 1 .. 10) {
        val selected = IntArray(size) { 0 }
        val visited = BooleanArray(numbs.size) { false }

        fun perm(depth:Int, end:Int) {
            if (depth == selected.size) {
                if (count == num) {
                    ans = selected.joinToString("")
                }

                count++
                return
            }

            for (i in 0 until end) {
                if (visited[i])
                    continue

                selected[depth] = numbs[i]

                visited[i] = true
                perm(depth + 1, numbs[i])
                visited[i] = false
            }
        }

        perm(0, 10)
    }

    println(ans?: -1)
}