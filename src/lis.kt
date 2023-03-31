fun main() {
    // n^2
    lis1()

    // n log n
    lis2()
}

fun lis1() {
    // n^2
    val numbs = listOf(3, 2, 6, 4, 5, 1)
    val lis = IntArray(numbs.size) { 0 }

    for (i in numbs.indices) {
        lis[i] = 1
        for (j in i downTo 0) {
            if (numbs[i] > numbs[j]) {
                lis[i] = lis[i].coerceAtLeast(lis[j] + 1)
            }
        }
    }

    println(lis.joinToString(" "))
}

fun lis2() {
    // n^2
    val numbs = listOf(3, 2, 6, 4, 5, 1)
    val lis = mutableListOf(numbs.first())

    for (num in numbs.drop(1)) {
        if (lis.last() < num) {
            lis += num
        } else {
            val idx = lis.binarySearch(num).let { if (it < 0) -it - 1 else it }
            lis[idx] = num
        }
    }

    println(lis.size)
}