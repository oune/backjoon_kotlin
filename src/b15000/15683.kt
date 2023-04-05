fun main() = with(System.`in`.bufferedReader()) {
    val (height, width) = readLine().split(" ").map { it.toInt() }
    val originMap = Array(height) {
        readLine().split(" ")
    }
    data class Camera(val x:Int, val y:Int, val type:Int)

    val cameras = mutableListOf<Camera>()
    for (i in originMap.indices) {
        for (j in originMap[i].indices) {
            if (originMap[i][j].toInt() in 1 .. 5) {
                cameras.add(Camera(j, i, originMap[i][j].toInt()))
            }
        }
    }

    var min = Int.MAX_VALUE
    val selected = IntArray(cameras.size) { 0 }
    fun perm(depth:Int) {
        if (depth == cameras.size) {
            val map = Array(originMap.size) {
                originMap[it].toTypedArray()
            }

            fun Camera.look(dx:Int, dy:Int) {
                var x = this.x + dx
                var y = this.y + dy

                while(y in map.indices && x in map[y].indices && map[y][x] != "6") {
                    if (map[y][x] == "0") {
                        map[y][x] = "#"
                    }

                    x += dx
                    y += dy
                }
            }

            fun Camera.look(direction:Int) {
                when(direction) {
                    0 -> { this.look(1, 0) }
                    1 -> { this.look(0, -1) }
                    2 -> { this.look(-1, 0) }
                    3 -> { this.look(0, 1) }
                }
            }

            fun Camera.watch(direction:Int) {
                when(this.type) {
                    1 -> {
                        this.look(direction)
                    }
                    2 -> {
                        this.look(direction)
                        this.look((direction + 2) % 4)
                    }
                    3 -> {
                        this.look(direction)
                        this.look((direction + 1) % 4)
                    }
                    4 -> {
                        for (i in 0..3) {
                            if (i == direction)
                                continue

                            this.look(i)
                        }
                    }
                    5 -> {
                        for (i in 0..3)
                            this.look(i)
                    }
                }
            }

            for (i in cameras.indices) {
                val camera = cameras[i]
                val direction = selected[i]
                camera.watch(direction)
            }

            val count = map.sumOf { list -> list.count { it == "0" } }
            min = min.coerceAtMost(count)
            return
        }
        for (i in 0 .. 3) {
            selected[depth] = i
            perm(depth + 1)
        }
    }
    perm(0)

    println(min)
}