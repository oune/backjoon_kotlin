import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with (BufferedReader(InputStreamReader(System.`in`))){
    val input = readLine()

    val questionReg = Regex("What is.*?\\?")
    val whatReg = Regex("^What")
    val questionMarkReg = Regex("\\?$")

    val sout = BufferedWriter(OutputStreamWriter(System.out))

    val matchResult = questionReg.findAll(input)
    matchResult.forEach {
        val pre = whatReg.replace(it.value, "Forty-two")
        val out = questionMarkReg.replace(pre, ".")
        sout.appendLine(out)
    }
    
    sout.flush()
    sout.close()
}