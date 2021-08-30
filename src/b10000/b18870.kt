package b10000

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine()!!.toInt()

    val list = readLine()!!.split(" ").map {
        it.toInt()
    }

    val composedList = list.sorted().distinct()

    val map = HashMap<Int, Int>()
    composedList.forEachIndexed { index, i ->
        map[i] = index
    }

    val bw = BufferedWriter(OutputStreamWriter(System.out))
    list.forEach{
        bw.write("${map[it]} ")
    }
    bw.flush()
    bw.close()
}


