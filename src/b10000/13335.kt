import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (truckCnt, bridgeWidth, maxWeight) = readLine().split(" ").map { it.toInt() }
    val waiting = LinkedList(readLine().split(" ").map { it.toInt() })
    val bridge = IntArray(bridgeWidth) { 0 }

    var count = 0
    fun IntArray.move() {
        if (this.first() != 0)
            count++


        for (i in 1..this.lastIndex) {
            this[i - 1] = this[i]
        }
        this[this.lastIndex] = 0
    }

    fun IntArray.enter(truck:Int) {
        this[this.lastIndex] = truck
    }

    var time = 0
    while (count != truckCnt) {
        val next = waiting.peek()
        bridge.move()

        if (next != null && bridge.sum() + next <= maxWeight) {
            bridge.enter(waiting.poll())
        }
        time++
    }

    println(time)
}