import kotlin.math.abs

fun main() {
    val (from, to) = readln().split(" ").map { it.toInt() }
    val buttonCnt = readln().toInt()
    val buttons = List(buttonCnt) {
        abs(readln().toInt() - to) + 1
    } + listOf(abs(from - to))

    val min = buttons.min()

    println(min)
}