package test.b6000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val sout = System.out.bufferedWriter()

    var isContinue = true
    while (isContinue) {
        val (l, r, c) = readLine().split(" ").map { it.toInt() }

        if (l == 0 && r == 0 && c == 0) {
            isContinue = false
        } else {
            val building = Array(l) {
                val list = Array(r) {
                    readLine().toCharArray()
                }
                readLine()

                list
            }
            val visited = Array(l) {
                Array(r) {
                    BooleanArray(c) {
                        false
                    }
                }
            }

            val que = LinkedList<State>()
            building.forEachIndexed { z, floor ->
                floor.forEachIndexed { y, list ->
                    list.forEachIndexed { x, char ->
                        if (char == 'S') {
                            que.offer(State(x, y, z, 0))
                            visited[z][y][x] = true
                        }
                    }
                }
            }

            var ans = -1
            while (que.isNotEmpty()) {
                val now = que.poll()

                if (building[now.z][now.y][now.x] == 'E') {
                    ans = now.time
                    break
                }

                listOf(
                    { state: State ->
                        val (x, y, z, time) = state
                        State(x + 1, y, z, time + 1)
                    },
                    { state: State ->
                        val (x, y, z, time) = state
                        State(x - 1, y, z, time + 1)
                    },
                    { state: State ->
                        val (x, y, z, time) = state
                        State(x, y + 1, z, time + 1)
                    },
                    { state: State ->
                        val (x, y, z, time) = state
                        State(x, y - 1, z, time + 1)
                    },
                    { state: State ->
                        val (x, y, z, time) = state
                        State(x, y, z + 1, time + 1)
                    },
                    { state: State ->
                        val (x, y, z, time) = state
                        State(x, y, z - 1, time + 1)
                    }
                ).forEach {
                    val moved = it(now)

                    if (building.isInBuilding(moved)) {
                        if (building[moved.z][moved.y][moved.x] != '#') {
                            if (!visited[moved.z][moved.y][moved.x]) {
                                que.offer(moved)
                                visited[moved.z][moved.y][moved.x] = true
                            }
                        }
                    }
                }
            }

            sout.appendLine(
                if (ans != -1)
                    "Escaped in $ans minute(s)."
                else
                    "Trapped!"
            )
        }
    }
    sout.flush()
    sout.close()
}

private fun Array<Array<CharArray>>.isInBuilding(state: State): Boolean {
    if (state.z in this.indices) {
        if (state.y in this[state.z].indices) {
            if (state.x in this[state.z][state.y].indices) {
                return true
            }
        }
    }
    return false
}

private data class State(val x: Int, val y: Int, val z: Int, val time: Int)