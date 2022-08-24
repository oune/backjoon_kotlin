package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    data class State(val time:Int, val money:Int)

    val arr = Array(n) {
        val (time, money) = readLine().split(" ").map { it.toInt() }
        State(time, money)
    }

    var max = 0
    val selected = BooleanArray(arr.size) {false}
    fun subSet(idx:Int) {
        if (idx == selected.size) {
            var profit = 0
            var i = 0
            while (i in arr.indices) {
                if (selected[i]) {
                    if (i + arr[i].time - 1 in arr.indices) {
                        profit += arr[i].money
                        i += arr[i].time

                        if (profit > max)
                            max = profit
                        continue
                    }
                }
                i++
            }

            return
        }

        listOf(false, true).forEach {
            selected[idx] = it
            subSet(idx + 1)
        }
    }

    subSet(0)
    println(max)
}