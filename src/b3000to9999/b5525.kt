package b3000to9999

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val s = readLine()!!

    print(count(n, m, s, 0, 0, 0))
}

tailrec fun count(n: Int, m: Int, s: String, acc: Int, count: Int, start: Int): Int = when {
    start >= m - 2 -> acc
    s[start] == 'I' && s[start + 1] == 'O' && s[start + 2] == 'I' -> {
        if (count + 1 == n)
            count(n, m, s, acc + 1, count, start + 2)
        else
            count(n, m, s, acc, count + 1, start + 2)
    }
    else -> count(n, m, s, acc, 0, start + 1)
}