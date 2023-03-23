/*
이분탐색

부스트의 속도가 조금 헷갈렸음.
반례
1
3 10 12
30 40 41
// 0

 */
fun main() = with(System.`in`.bufferedReader()) {
    val testcaseCnt = readLine().toInt()
    val sb = StringBuilder()

    for(i in 0 until testcaseCnt) {
        val (_, distance, limit) = readLine().split(" ").map { it.toInt() }
        val speeds = readLine().split(" ").map { it.toInt() }
        val rivals = speeds.dropLast(1)
        val my = speeds.last()

        val max = rivals.max()
        if (my > max) {
            sb.appendLine(0)
            continue
        }

        val rival = distance / max.toDouble()

        var ans = -1
        var low = 0
        var high = limit
        while (low <= high) {
            val mid = (low + high).ushr(1) // safe from overflows

            val time = (distance - mid) / my.toDouble() + 1

            if (time >= rival) {
                low = mid + 1
            } else {
                high = mid - 1
                ans = mid
            }
        }

        if (ans == -1) {
            sb.appendLine(-1)
        } else if (ans <= my) {
            sb.appendLine(0)
        } else {
            sb.appendLine(ans)
        }
    }

    print(sb)
}