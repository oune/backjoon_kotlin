package b10000

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val ans = Array(readLine().toInt()) { 0 }
    val num = readLine().split(" ").map{ it.toInt() }.reversed()
    val stack = ArrayList<Int>()

    num.forEachIndexed { index, i ->
        while(stack.isNotEmpty() && stack.last() <= i) {
            stack.removeLast()
        }
        ans[ans.size - 1- index] = if (stack.isNotEmpty()) -1 else stack.last()

        stack.add(i)
    }


    val out = BufferedWriter(OutputStreamWriter(System.out))
    ans.forEach {
        out.append("$it ")
    }
    out.flush()
    out.close()
}