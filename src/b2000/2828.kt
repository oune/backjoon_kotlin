fun main () {
    val (n, m) = readln().split(" ").map { it.toInt() }
    var start = 1
    var end = m

    var total = 0
    repeat(readln().toInt()) {
        val pos = readln().toInt()

        if (pos < start) {
            val distance = start - pos
            start -= distance
            end -= distance
            total += distance
        } else if (end < pos) {
            val distance = pos - end
            start += distance
            end += distance
            total += distance
        }
    }
    println(total)
}