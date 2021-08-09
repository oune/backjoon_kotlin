import com.sun.org.apache.xpath.internal.operations.Bool
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min
import kotlin.math.pow

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val sout = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { readLine().split("").filter { it != "" }.map{ it.toInt() }.toTypedArray() }

    val size = min(n , m)

    print(count(arr, n, m, size).toDouble().pow(2).toInt())
}

fun count(arr: Array<Array<Int>>, n: Int, m: Int, size: Int): Int {
    (size downTo 2).forEach {
        if (check(arr, n, m, size - 1)) {
            return it
        }
    }
    return 1
}

fun check(arr: Array<Array<Int>>, n: Int, m: Int, size: Int) :Boolean{
    (0 until m - size).forEach { i ->
        (0 until n - size).forEach { j ->
            if (arr[j][i] == arr[j][i + size] && arr[j][i + size] == arr[j + size][i] && arr[j + size][i] == arr[j + size][i + size] && arr[j + size][i + size] == arr[j][i]) {
                return true
            }
        }
    }
    return false
}