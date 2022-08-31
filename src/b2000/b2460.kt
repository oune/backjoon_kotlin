package test.b2000

fun main() = with(System.`in`.bufferedReader()) {
    var max = 0
    var people = 0
    repeat(10) {
        val (outcome, income) = readLine().split(' ').map { it.toInt() }
        people += income - outcome

        if (people > max)
            max = people
    }

    println(max)
}