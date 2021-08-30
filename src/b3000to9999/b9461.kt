package b3000to9999

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val stout = BufferedWriter(OutputStreamWriter(System.out))
    val test = readLine()!!.toInt()

    repeat(test) {
        val input = readLine()!!.toLong()
        stout.append(p(input).toString())
        stout.newLine()
    }

    stout.flush()
    stout.close()
}
fun p(num :Long): Long = p(num, 1, 1, 1)

tailrec fun p(num: Long, first: Long, second: Long, third: Long) : Long = when (num){
    1L -> first
    2L -> second
    3L -> third
    else -> p(num - 1, second, third, first + second)
}