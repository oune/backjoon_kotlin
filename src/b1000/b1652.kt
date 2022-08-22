package test.b1000

fun main() {
    val size = readLine()!!.toInt()
    val arr = Array(size) {
        readLine()!!.toCharArray()
    }

    var horizon = 0
    for (i in 0 until size) {
        var count = 0
        var counted = false

        for (j in 0 until size) {
            val now = arr[i][j]

            if (now == '.')
                count++
            else {
                count = 0
                counted = false
            }

            if (!counted && count == 2) {
                counted = true
                horizon++
            }
        }
    }

    var vertical = 0
    for (j in 0 until size) {
        var count = 0
        var counted = false

        for (i in 0 until size) {
            val now = arr[i][j]

            if (now == '.')
                count++
            else {
                count = 0
                counted = false
            }

            if (!counted && count == 2) {
                counted = true
                vertical++
            }
        }
    }
    println("$horizon $vertical")
}