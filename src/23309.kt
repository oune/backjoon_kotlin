import kotlin.text.StringBuilder

fun main() = with(System.`in`.bufferedReader()) {
    val (n, jobCnt) = readLine().split(" ").map { it.toInt() }
    val stations = readLine().split(" ").map { it.toInt() }.toMutableList()

    fun MutableList<Int>.insertBefore(target:Int, new:Int):Int {
        val idx = this.indexOf(target)
        val pre = if (idx - 1 in this.indices) this[idx - 1] else this.last()
        this.add(if (idx == 0) 0 else idx, new)

        return pre
    }

    fun MutableList<Int>.insertAfter(target:Int, new:Int):Int {
        val idx = this.indexOf(target)
        val next = if (idx + 1 in this.indices) this[idx + 1] else this.first()
        this.add(idx + 1, new)

        return next
    }

    fun MutableList<Int>.removeAfter(target:Int): Int {
        val idx = this.indexOf(target)
        return this.removeAt(if (idx == this.lastIndex) 0 else idx + 1)
    }

    fun MutableList<Int>.removeBefore(target:Int): Int {
        val idx = this.indexOf(target)
        return this.removeAt(if (idx == 0) this.lastIndex else idx - 1)
    }

    val sb = StringBuilder()
    repeat(jobCnt) {
        val inputs = readLine().split(" ")
        when(inputs[0]) {
            "BN" -> {
                sb.appendLine(stations.insertAfter(inputs[1].toInt(), inputs[2].toInt()))
            }
            "BP" -> {
                sb.appendLine(stations.insertBefore(inputs[1].toInt(), inputs[2].toInt()))
            }
            "CN" -> {
                sb.appendLine(stations.removeAfter(inputs[1].toInt()))
            }
            "CP" -> {
                sb.appendLine(stations.removeBefore(inputs[1].toInt()))
            }
        }
    }
    print(sb)
}