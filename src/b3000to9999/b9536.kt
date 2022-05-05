package test.b3000to9999

import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val tryCnt = readLine().toInt()
    val bw = BufferedWriter(OutputStreamWriter(System.out));

    repeat(tryCnt) {
        var sound = readLine().split(" ")

        while (true) {
            val input = readLine().split(" ")

            if (input[1] == "does")
                break;

            sound = sound.filter {
                it != input[2]
            }
        }

        sound.forEach {
            bw.append(it)
            bw.append(" ")
        }
        bw.newLine()
    }

    bw.flush()
    bw.close()
}