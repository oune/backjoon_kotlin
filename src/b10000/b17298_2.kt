package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val arr = readLine().split(" ").map { it.toInt() }
    val rightBigIntegers = IntArray(arr.size)
    rightBigIntegers[rightBigIntegers.lastIndex] = -1

    for (i in rightBigIntegers.lastIndex - 1 downTo 0) {
        val now = arr[i]
        val next = arr[i+1]
        val nextRightBigIntger = rightBigIntegers[i + 1]

        rightBigIntegers[i] = if (now < next) {
            next
        } else if (now < nextRightBigIntger) {
            nextRightBigIntger
        } else {
            -1
        }
    }

    val out = System.out.bufferedWriter()
    rightBigIntegers.forEach {
        out.append("$it ")
    }
    out.flush()
}