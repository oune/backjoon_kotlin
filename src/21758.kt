

fun main() {
    readln()
    val honeys = readln().split(" ").map { it.toInt() }

    fun List<Int>.sol():Int {
        val prefixSum = this.scan(0) { acc, i -> acc + i}.drop(1)

        val first = prefixSum.mapIndexed { index, i -> i - this[index]}.last()
        val second = prefixSum.dropLast(1).mapIndexed { index, i -> i - this[index] * 2 }.max()

        return first + second
    }
    val forward = honeys.sol()
    val backward = honeys.asReversed().sol()

    fun mid():Int {
        val sliced = honeys.slice(1 until honeys.lastIndex)
        val sum = sliced.sum()
        val max = sliced.max()

        return sum + max
    }

    val res = maxOf(forward, backward, mid())
    println(res)
}
