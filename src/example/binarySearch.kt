fun main() {
    val num = listOf(1, 4)

    val target = 3
    val lower = num.binarySearch(target).let { if (it < 0) -it - 2 else it }
    val upper = num.binarySearch(target).let { if (it < 0) -it - 1 else it }

    println("$lower $upper")
}