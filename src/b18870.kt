import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine()!!.toInt()

    val list = readLine()!!.split(" ").map {
        it.toInt()
    }

    val composedList = list.sorted().distinct().toIntArray()

    val map = HashMap<Int, Int>()
    composedList.forEachIndexed { index, i ->
        map[i] = index
    }

    list.forEach{
        print("${map[it]} ")
    }
}