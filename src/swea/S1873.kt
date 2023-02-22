fun main() = with(System.`in`.bufferedReader()) {
    val testcase = readLine().toInt()
    val bw = System.out.bufferedWriter()

    repeat(testcase) { testCaseIdx ->
        val (height, width) = readLine().split(" ").map { it.toInt() }

        val map = List(height) {
            readLine().toCharArray()
        }
        val inputCnt = readLine().toInt()
        val commands = readLine().toList()
        data class Point(val x:Int, val y:Int)

        var tank = Point(0, 0)
        for (i in map.indices) {
            for (j in map[i].indices) {
                when (map[i][j]) {
                    '^', 'v', '<', '>' -> {
                        tank = Point(j, i)
                    }
                }
            }
        }

        fun Point.move(dx:Int, dy:Int):Point {
            map[this.y][this.x] = if (dx == -1) {
                '<'
            } else if (dx == 1) {
                '>'
            } else if (dy == 1) {
                'v'
            } else if (dy == -1) {
                '^'
            } else 'X'

            val movedX = this.x + dx
            val movedY = this.y + dy

            if (movedY in map.indices && movedX in map[movedY].indices) {
                if (map[movedY][movedX] == '.') {
                    map[movedY][movedX] = map[this.y][this.x]
                    map[this.y][this.x] = '.'
                    return Point(movedX, movedY)
                }
            }

            return Point(this.x, this.y)
        }

        fun Point.shoot(dx:Int, dy:Int) {
            var x = this.x + dx
            var y = this.y + dy

            while(y in map.indices && x in map[y].indices) {
                when (map[y][x]) {
                    '*' -> {
                        map[y][x] = '.'
                        break
                    }
                    '#' -> {
                        break
                    }
                }

                x += dx
                y += dy
            }
        }

        fun Point.shoot() {
            when(map[this.y][this.x]) {
                '^' -> {tank.shoot(0, -1)}
                'v' -> {tank.shoot(0, 1)}
                '<' -> {tank.shoot(-1, 0)}
                '>' -> {tank.shoot(1, 0)}
            }
        }

        for (command in commands) {
            when(command) {
                'U' -> { tank = tank.move(0, -1) }
                'D' -> { tank = tank.move(0, 1) }
                'L' -> { tank = tank.move(-1, 0) }
                'R' -> { tank = tank.move(1, 0) }
                'S' -> { tank.shoot() }
            }
        }

        bw.append("#${testCaseIdx + 1} ")
        for (line in map) {
            bw.appendLine(line.joinToString(""))
        }
    }

    bw.flush()
    bw.close()
}