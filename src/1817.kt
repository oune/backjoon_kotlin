import kotlin.system.exitProcess

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    if (n == 0) {
        println(0)
        exitProcess(0)
    }

    val numbs = readln().split(" ").map { it.toInt() }
    var count = 1
    var box = 0
    for (numb in numbs) {
        if (numb + box <= m) {
            box += numb
        } else {
            box = numb
            count++
        }
    }

    println(count)
}