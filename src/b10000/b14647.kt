package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map { it.toInt() }
    val arr = Array(n) {
        readLine().split(' ').map { it.toInt() }
    }

    val lines = IntArray(n) { 0 }
    val columns = IntArray(m) { 0 }

    arr.forEachIndexed { i, list ->
        list.forEachIndexed { j, box ->
            var count = 0
            var num = box
            while (num > 0) {
                if (num % 10 == 9)
                    count++
                num /= 10
            }
            lines[i] += count
            columns[j] += count
        }
    }

    val ans = lines.sum() - lines.maxOrNull()!!.coerceAtLeast(columns.maxOrNull()!!)
    println(ans)
}