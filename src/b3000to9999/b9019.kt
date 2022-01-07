package test.b3000to9999

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    BufferedWriter(OutputStreamWriter(System.out)).use { sout ->
        val sb = StringBuilder()

        val case = readLine().toInt()
        repeat(case) {
            val (a, b) = readLine().split(" ").map{ it.toInt() }

            val visited = BooleanArray(10000) { false }
            val que = LinkedList<Pair<Int, String>>()
            que.offer(Pair(a, ""))

            while(que.isNotEmpty()) {
                val (num, acc) = que.poll()

                if (visited[num])
                    continue

                visited[num] = true

                if (num == b) {
                    sb.appendLine(acc)
                    break
                }

                que.offer(d(num, acc))
                que.offer(s(num, acc))
                que.offer(l(num, acc))
                que.offer(r(num, acc))
            }
        }

        sout.append(sb.toString())
        sout.flush()
    }
}

fun d(num:Int, acc:String) :Pair<Int, String> {
    return Pair(2 * num % 10000, acc + "D")
}

fun s(num:Int, acc:String): Pair<Int, String> {
    return Pair(if (num - 1 < 0) 9999 else num - 1, acc + "S")
}

fun l(num:Int, acc:String): Pair<Int, String> {
    return Pair(num * 10 % 10000 + num / 1000, acc + "L")
}

fun r(num:Int, acc:String): Pair<Int, String> {
    return Pair(num / 10 + num % 10 * 1000, acc + "R")
}