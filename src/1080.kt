fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val origin = List(n) {
        readln().map { it.digitToInt() }.toIntArray()
    }
    val to = List(n) {
        readln().map { it.digitToInt() }
    }

    var count = 0
    for (i in 0 .. origin.lastIndex - 2) {
        for (j in 0 .. origin[i].lastIndex - 2) {
            if (origin[i][j] != to[i][j]) {
                count++

                for (dy in 0 .. 2) {
                    for (dx in 0 .. 2) {
                        origin[i + dy][j + dx] = when(origin[i + dy][j + dx]) {
                            0 -> { 1 }
                            1 -> { 0 }
                            else -> { 9 }
                        }
                    }
                }
            }
        }
    }

    val isSame = (0..origin.lastIndex).all { i ->
        (0..origin[i].lastIndex).all { j ->
            origin[i][j] == to[i][j]
        }
    }

    println(if (isSame) count else -1)
}