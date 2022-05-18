package b9000

import kotlin.math.sqrt

private val out = System.out.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val size = 10001
    val che = Array(size){ true }
    che[0] = false
    che[1] = false
    for (i in 2..sqrt(size.toDouble()).toInt()) {
        if (che[i]) {
            var idx = i * i
            while (idx < size) {
                che[idx] = false
                idx += i
            }
        }
    }

    val testCnt = readLine().toInt()
    repeat(testCnt) {
        val num = readLine().toInt()
        for (i in 0..num/2) {
            if (che[num / 2 - i] && che[num / 2+ i]) {
                out.appendLine("${num / 2-i} ${num / 2+i}")
                break
            }
        }
    }
    out.flush()
}