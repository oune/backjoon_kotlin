fun main() {
    val n = readln().toLong()


    var sum = 0L
    var last = 1L
    for (i in 1 .. n) {
        sum += i

        if (sum > n) {
            last = i - 1
            break
        }
    }

    println(last)
}