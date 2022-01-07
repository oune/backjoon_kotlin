package test.example

fun main() {
    val (points, lines) = readLine()!!.split(" ").map { it.toInt() }

    val arr = Array(points) {Array<Int>(points){0} }

    repeat(lines) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        arr[a][b] = 1
        arr[b][a] = 1
    }

    print(arr.contentDeepToString())
}