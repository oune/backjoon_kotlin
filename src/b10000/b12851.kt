//package test.b10000
//
//import java.util.*
//
//fun main() = with(System.`in`.bufferedReader()) {
//    val (n, sister) = readLine().split(" ").map { it.toInt() }
//    val size = 100000 + 1
//    val visitedTime = IntArray(size) { -1 }
//
//    var min = Int.MAX_VALUE
//    var count = 0
//
//    val que = LinkedList<PositionState>()
//    que.offer(PositionState(n, 0))
//    visitedTime[n] = 0
//
//    while(que.isNotEmpty()) {
//        val now = que.poll()
//
//        if (now.subin == sister) {
//            min = now.time
//            count++
//            continue
//        }
//
//        listOf(
//            {state: PositionState ->
//                val (subin, time) = state
//                PositionState(subin + 1, time + 1)
//            },
//            {state: PositionState ->
//                val (subin, time) = state
//                PositionState(subin - 1, time + 1)
//            },
//            {state: PositionState ->
//                val (subin, time) = state
//                PositionState(subin * 2, time + 1)
//            },
//        ).forEach {
//            val moved = it(now)
//
//            if (moved.subin in visitedTime.indices) {
//                if (visitedTime[moved.subin] == -1 || visitedTime[moved.subin] == moved.time) {
//                    que.offer(moved)
//                    visitedTime[moved.subin] = moved.time
//                }
//            }
//        }
//    }
//
//
//    println(min)
//    println(count)
//}
//
//private data class State(val subin:Int, val time:Int)