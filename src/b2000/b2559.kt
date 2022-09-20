package b2000

fun main() = with(System.`in`.bufferedReader()) {
    val (_, k) = readLine().split(' ').map { it.toInt() }
    val temperatures = readLine().split(' ').map { it.toInt() }

    var sum = temperatures.slice(0 until k).sum()
    var max = sum

    (k..temperatures.lastIndex).forEach {
        sum += temperatures[it] - temperatures[it - k]

        if (sum > max)
            max = sum
    }
    println(max)
}