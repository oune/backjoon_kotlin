import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine()
    val reg = Regex("pi|ka|chu")
    val out = BufferedWriter(OutputStreamWriter(System.out))
    val res = input.replace(reg, "")

    if (res.isNotEmpty()) {
        out.append("NO")
    } else {
        out.append("YES")
    }
    out.flush()
    out.close()
}