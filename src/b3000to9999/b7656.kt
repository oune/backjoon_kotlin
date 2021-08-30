package b3000to9999

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with (BufferedReader(InputStreamReader(System.`in`))){
    val sout = BufferedWriter(OutputStreamWriter(System.out))

    val input = readLine()
    val tokens = input.split("What").filter { it.contains("?")}

    tokens.forEach {
        sout.append("Forty-two")
        sout.append(it.substring(0, it.indexOf("?")))
        sout.appendLine(".")
    }
    
    sout.flush()
    sout.close()
}