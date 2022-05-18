package b9000

import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val sout = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val (w, h) = readLine().split(" ").map { it.toInt() }

        if (w == 0 && h == 0) {
            break
        }

        val arr = Array(h) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }
        val visited = Array(h) {
            BooleanArray(w) { false }
        }

        var count = 0
        for (i in 0 until h) {
            for (j in 0 until w) {
                if (!visited[i][j] && arr[i][j] == 1) {
                    count++
                    dfs(arr, visited, i, j)
                }
            }
        }

        sout.appendLine(count.toString())
    }

    sout.flush()
    sout.close()
}

fun dfs(arr:Array<IntArray>, visited:Array<BooleanArray>, i:Int, j:Int) {
    if (i < 0 || i >= arr.size)
        return
    if (j < 0 || j >= arr[i].size)
        return
    if (visited[i][j])
        return
    if (arr[i][j] == 0)
        return

    visited[i][j] = true
    dfs(arr, visited, i - 1, j - 1)
    dfs(arr, visited, i, j - 1)
    dfs(arr, visited, i - 1, j)
    dfs(arr, visited, i + 1, j + 1)
    dfs(arr, visited, i + 1, j)
    dfs(arr, visited, i, j + 1)
    dfs(arr, visited, i - 1, j + 1)
    dfs(arr, visited, i + 1, j - 1)
}