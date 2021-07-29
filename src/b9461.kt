import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val stout = BufferedWriter(OutputStreamWriter(System.out))
    val test = readLine()!!.toInt()

    repeat(test) {
        val input = readLine()!!.toInt()
        stout.append(p(input).toString())
        stout.newLine()
    }

    stout.flush()
    stout.close()
}
fun p(num :Int): Int = p(num, 1, 1, 1)

tailrec fun p(num: Int, first: Int, second: Int, third: Int) : Int = when (num){
    1 -> first
    2 -> second
    3 -> third
    else -> p(num - 1, second, third, first + second)
}