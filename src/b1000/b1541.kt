package b1000

import java.io.BufferedReader

fun main() = with(System.`in`.bufferedReader()) {
    var minusFlag = false

    val sum = readLine()
        .replace("+", " ")
        .replace("-", " -")
        .split(" ")
        .map{
        it.toInt()
    }.fold(0) { total, data ->
        if (minusFlag) {
            if (data > 0)
                total - data
            else
                total + data
        } else {
            if (data < 0)
                minusFlag = true;

            total + data
        }
    }

    print(sum)
}