package b1000

fun main() = with(System.`in`.bufferedReader()) {
    val case = readLine().toInt()
    repeat(case) {
        val (n, m) = readLine().split(" ").map { it.toInt() }

        var num = 1
        for (i in 0 .. m - n) {
            num *= (m - i)
        }
        println(num)
    }
}