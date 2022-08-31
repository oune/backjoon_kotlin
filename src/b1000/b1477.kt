package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (_, target, load) = readLine().split(' ').filter{ it != ""}.map { it.toInt() }
    val arr = readLine().split(' ').filter{ it != ""}.map { it.toInt() }.sorted().toList() + listOf(load)
    var pre = 0
    val distances = arr.map {
        val distance = it - pre - 1
        pre = it
        distance
    }

    var low = 1
    var high = load
    while (low <= high) {
        val mid = (low + high) / 2
        val cnt = distances.fold(0) { acc, distance ->
            acc + distance / mid
        }

        if (cnt > target)
            low = mid + 1
        else
            high = mid - 1
    }

    println(low)
}