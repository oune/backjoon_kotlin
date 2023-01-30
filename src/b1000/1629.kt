package b1000fun main() = with(System.`in`.bufferedReader()) {
    val (a, b, c) = readLine().split(" ").map { it.toLong() }

    fun pow(num:Long, n:Long):Long {
        if (n == 1L)
            return num % c

        val temp = pow(num, n / 2)

        return if (n % 2 == 0L)
            (temp * temp) % c
        else
            (temp * temp % c) * num % c
    }

    println(pow(a, b))
}