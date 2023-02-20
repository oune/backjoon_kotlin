fun main() = with(System.`in`.bufferedReader()) {
    val (n, _, k) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    data class Operation(val x:Int, val y:Int, val size:Int)

    val operations = Array(k) {
        val (r, c, s) = readLine().split(" ").map { it.toInt() }
        Operation(c - 1, r - 1, s)
    }

    fun Array<IntArray>.turn(x:Int, y:Int, maxSize:Int): Array<IntArray> {
        val res = Array(this.size) {
            IntArray(this[it].size) { 0 }
        }

        for (i in this.indices) {
            for (j in this[i].indices) {
                res[i][j] = this[i][j]
            }
        }

        fun right(start:Int, end:Int, y:Int) {
            for (i in start..end) {
                res[y][i + 1] = this[y][i]
            }
        }

        fun left(start:Int, end:Int, y:Int) {
            for (i in start..end) {
                res[y][i - 1] = this[y][i]
            }
        }

        fun up(start:Int, end:Int, x:Int) {
            for (i in start..end) {
                res[i - 1][x] = this[i][x]
            }
        }

        fun down(start:Int, end:Int, x:Int) {
            for (i in start..end) {
                res[i + 1][x] = this[i][x]
            }
        }

        for (i in 1 .. maxSize) {
            right(x - i, x + i - 1, y - i)
            left(x - i + 1, x + i, y + i)
            up(y - i + 1, y + i, x - i)
            down(y - i, y + i - 1, x + i)
        }

        return res
    }
    var min = Int.MAX_VALUE
    val order = Array<Operation?>(operations.size) { null }
    fun perm(cnt:Int, flag:Int) {
        if (cnt == operations.size) {
            var moved = arr
            order.forEach { oper ->
                moved = moved.turn(oper!!.x, oper.y, oper.size)
            }

            val num = moved.minOf {
                it.sum()
            }

            if (num < min)
                min = num

            return
        }

        for (i in operations.indices) {
            if (flag and (1 shl i) != 0)
                continue

            order[cnt] = operations[i]
            perm(cnt + 1, flag or (1 shl i))
        }
    }
    perm(0, 0)

    println(min)
}