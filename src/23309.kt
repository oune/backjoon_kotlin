import java.util.StringTokenizer
import kotlin.text.StringBuilder

fun main() = with(System.`in`.bufferedReader()) {
    val (_, jobCnt) = readLine().split(" ").map { it.toInt() }
    val stations = readLine().split(" ").map { it.toInt() }

    val arraySize = 1000001
    val preStation = IntArray(arraySize) { -1 }
    val nextStation = IntArray(arraySize) { -1 }

    for (i in stations.indices) {
        val preIndex = if (i - 1 in stations.indices) i - 1 else stations.lastIndex
        val nextIndex = if (i + 1 in stations.indices) i + 1 else 0

        val pre = stations[preIndex]
        val next = stations[nextIndex]

        preStation[stations[i]] = pre
        nextStation[stations[i]] = next
    }

    fun bn(target:Int, new:Int): Int {
        val next = nextStation[target]

        nextStation[target] = new
        preStation[next] = new
        nextStation[new] = next
        preStation[new] = target

        return next
    }

    fun bp(target:Int, new:Int): Int {
        val pre = preStation[target]

        preStation[target] = new
        nextStation[pre] = new
        preStation[new] = pre
        nextStation[new] = target

        return pre
    }

    fun cn(target:Int): Int {
        val next = nextStation[target]

        nextStation[target] = nextStation[next]
        preStation[nextStation[next]] = target
        nextStation[next] = -1
        preStation[next] = -1

        return next
    }

    fun cp(target:Int): Int {
        val pre = preStation[target]

        nextStation[preStation[pre]] = target
        preStation[target] = preStation[pre]
        nextStation[pre] = -1
        preStation[pre] = -1

        return pre
    }

    val sb = StringBuilder()
    repeat(jobCnt) {
        val st = StringTokenizer(readLine())
        when(st.nextToken()) {
            "BN" -> {
                sb.appendLine(bn(st.nextToken().toInt(), st.nextToken().toInt()))
            }
            "BP" -> {
                sb.appendLine(bp(st.nextToken().toInt(), st.nextToken().toInt()))
            }
            "CN" -> {
                sb.appendLine(cn(st.nextToken().toInt()))
            }
            "CP" -> {
                sb.appendLine(cp(st.nextToken().toInt()))
            }
        }
    }

    println(sb)
}