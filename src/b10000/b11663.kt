package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (_, m) = readLine().split(" ").map{ it.toInt() }
    val points = readLine().split(" ").map{ it.toInt() }.sorted()
    val sout = System.out.bufferedWriter()

    repeat(m) {
        val (start, end) = readLine().split(" ").map{ it.toInt() }

        val leftIdx = points.binarySearchL(start)
        val rightIdx = points.binarySearchR(end)
        val cnt = rightIdx - leftIdx

        sout.appendLine("$cnt")
    }
    sout.close()
}

private fun List<Int>.binarySearchL(target: Int): Int {
    var left = 0
    var right = this.lastIndex
    while(left <= right) {
        val mid = (left + right) / 2

        if (this[mid] < target) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return left
}

private fun List<Int>.binarySearchR(target: Int): Int {
    var left = 0
    var right = this.lastIndex
    while(left <= right) {
        val mid = (left + right) / 2

        if (this[mid] > target) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return right + 1
}