import org.junit.jupiter.api.assertTimeoutPreemptively

fun main() {
    readln()
    val honeys = readln().split(" ").map { it.toInt() }

    fun List<Int>.sol():Int {
        val accSum = this.scan(0) {acc, i -> acc + i}.drop(1)

        val (first, index) = accSum.mapIndexed { index, i -> Pair(i - this[index], index) }.maxBy { it.first }

        val list = accSum.slice((0 until index) + (index + 1 until accSum.lastIndex))

        val second = list.mapIndexed { index, i -> i - this[index] * 2 }.max()

        return first + second
    }
    val forward = honeys.sol()
    val backward = honeys.asReversed().sol()
    val res = maxOf(forward, backward)

    println(res)

}
