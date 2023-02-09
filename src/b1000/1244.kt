import kotlin.math.ceil

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val switches = readLine().split(" ").map { it.toInt() }.toIntArray()

    fun IntArray.toggle(idx:Int) {
        this[idx] = if (this[idx] == 1) 0 else 1
    }

    val students = readLine().toInt()
    repeat(students) {
        val (gender, i) = readLine().split(" ").map { it.toInt() }
        val num = i - 1

        when (gender) {
            1 -> {
                var idx = num
                while (idx in switches.indices) {
                    switches.toggle(idx)
                    idx += i
                }
            }
            2 -> {
                switches.toggle(num)

                var idx = 1
                var left = num - idx
                var right = num + idx
                while (right in switches.indices && left in switches.indices) {
                    if (switches[left] == switches[right]) {
                        switches.toggle(left)
                        switches.toggle(right)
                    } else {
                        break
                    }
                    idx++
                    left = num - idx
                    right = num + idx
                }
            }
        }
    }

    val res = switches.toList().chunked(20).joinToString("\n") {
        it.joinToString(" ")
    }

    print(res)
}