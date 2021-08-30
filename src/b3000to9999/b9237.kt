package b3000to9999

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    readLine()
    val trees = readLine().split(" ").map { it.toInt() }.sortedDescending().toMutableList()

    trees.forEachIndexed { index, i -> // 매핑으로 변경 가능성은 없나?
        trees[index] = i + index + 2
    }
    val max = trees.maxOf { it }

    val out = BufferedWriter(OutputStreamWriter(System.out))
    out.append(max.toString())
    out.flush()
}