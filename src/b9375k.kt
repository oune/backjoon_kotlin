import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.collections.HashMap

fun main() = with (BufferedReader(InputStreamReader(System.`in`))) {
    val testCase = readLine()!!.toInt()

    repeat(testCase) {
        val clothNum = readLine()!!.toInt()
        val closet = HashMap<String, Int>()

        repeat(clothNum) {
            val (name, type) = readLine().split(" ")

            closet[type] = closet.getOrDefault(type, 0) + 1
        }

        var res = 1
        closet.forEach {
            res *= it.value + 1
        }
        res--

        println(res)
    }
}