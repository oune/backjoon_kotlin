package b2000fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val map =  Array(size) {
        readLine().split(" ").map { it.toInt() }
    }
    var blue = 0
    var white = 0

    fun Array<List<Int>>.check(x:Int, y:Int, size:Int):Int {
        if (size == 1) {
            val color = map[y][x]
            if (color == 1)
                blue++
            else
                white++

            return color
        }

        val halfSize = size / 2
        val ans = listOf(
            this.check(x, y, halfSize),
            this.check(x, y - halfSize, halfSize),
            this.check(x - halfSize, y, halfSize),
            this.check(x - halfSize, y - halfSize, halfSize),
        )

        if (ans.all { it == 1 }) {
            blue -= 3
            return 1
        }

        if (ans.all { it == 0 }) {
            white -= 3
            return 0
        }

        return -1
    }

    fun Array<List<Int>>.check() {
        this.check(map[map.lastIndex].lastIndex, map.lastIndex, map.size)
    }

    map.check()

    println("$white")
    println("$blue")
}
