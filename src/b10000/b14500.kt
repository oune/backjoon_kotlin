package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { readLine().split(" ").map { it.toInt() } }

    val tetrominos = getTetrominos()

    var maxSum = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            val now = Pair(i, j)

            tetrominos.forEach {
                val sum = arr.count(now, it)
                if (maxSum < sum)
                    maxSum = sum
            }
        }
    }

    print(maxSum)
}

private fun getTetrominos() :List<List<Pair<Int, Int>>>{
    val left = Pair(0, -1)
    val right = Pair(0, 1)
    val up = Pair(-1, 0)
    val down = Pair(1, 0)

    val tetrominoI = listOf(up, up * 2, down)
    val tetrominoO = listOf(down, right, right + down)
    val tetrominoZ = listOf(left, down, right + down)
    val tetrominoS = tetrominoZ.mirror()
    val tetrominoJ = listOf(left, up, up * 2)
    val tetrominoL = tetrominoJ.mirror()
    val tetrominoT = listOf(left, down, right)

    return listOf(
        tetrominoI,
        tetrominoI.turn90(),
        tetrominoO,
        tetrominoZ,
        tetrominoZ.turn90(),
        tetrominoS,
        tetrominoS.turn90(),
        tetrominoL,
        tetrominoL.turn90(),
        tetrominoL.turn180(),
        tetrominoL.turn270(),
        tetrominoJ,
        tetrominoJ.turn90(),
        tetrominoJ.turn180(),
        tetrominoJ.turn270(),
        tetrominoT,
        tetrominoT.turn90(),
        tetrominoT.turn180(),
        tetrominoT.turn270(),
    )
}

private fun Pair<Int, Int>.turnRight(): Pair<Int, Int> {
    return Pair(this.second, -this.first)
}

private fun List<Pair<Int,Int>>.turnRight(): List<Pair<Int, Int>> {
    return this.map { 
        it.turnRight()
    }
}

private fun List<Pair<Int,Int>>.turn90(): List<Pair<Int, Int>> {
    return this.turnRight()
}

private fun List<Pair<Int,Int>>.turn180(): List<Pair<Int, Int>> {
    return this.turn90().turnRight()
}

private fun List<Pair<Int,Int>>.turn270(): List<Pair<Int, Int>> {
    return this.turn180().turnRight()
}

private fun Pair<Int, Int>.mirror(): Pair<Int, Int> {
    return Pair(this.first, -this.second)
}

private fun List<Pair<Int,Int>>.mirror(): List<Pair<Int, Int>> {
    return this.map {
        it.mirror()
    }
}

private operator fun Pair<Int, Int>.plus(b: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + b.first, this.second + b.second)
}

private operator fun Pair<Int, Int>.times(b: Int): Pair<Int, Int> {
    return Pair(this.first * b, this.second * b)
}

private fun Array<List<Int>>.get(point: Pair<Int, Int>): Int {
    return this.get(point.first, point.second)
}
private fun Array<List<Int>>.get(i: Int, j: Int): Int {
    if (i in this.indices)
        if (j in this[i].indices)
            return this[i][j]

    return 0
}

private fun Array<List<Int>>.count(point: Pair<Int, Int>, tetromino: List<Pair<Int,Int>>): Int {
    return tetromino.fold( this.get(point) ) { acc , pair ->
        val moved = point + pair
        acc + this.get(moved)
    }
}