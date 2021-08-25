//fun main() = with(System.`in`.bufferedReader()) {
//    val testCnt = readLine().toInt()
//
//    repeat(testCnt) {
//        val (countryCnt, airplaneCnt) = readLine().split(" ").map { it.toInt() }
//        val set = Set(countryCnt + 1)
//        var count = 0;
//        repeat(airplaneCnt) {
//            val (a, b) = readLine().split(" ").map { it.toInt() }
//
//            if (set.findSet(a) != set.findSet(b)) {
//               count++
//               set.union(a, b)
//            }
//        }
//        println(count)
//    }
//}
//
////private class Set(size :Int) {
////    val data = Array(size) {it}
////
////    fun findSet(p:Int): Int {
////        if (p != data[p])
////            data[p] = findSet(data[p])
////        return data[p]
////    }
////
////    fun union(a:Int, b:Int) {
////        val ap = findSet(a)
////        val bp = findSet(b)
////        data[ap] = bp
////    }
////}