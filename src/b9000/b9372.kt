package b9000//fun b1000.b3000to9999.b10000.main() = with(System.`in`.bufferedReader()) {
//    val testCnt = readLine().toInt()
//
//    repeat(testCnt) {
//        val (countryCnt, airplaneCnt) = readLine().split(" ").map { it.toInt() }
//        val set = Set(countryCnt + 1)
//        var `1000~1999`.b1000.b3000to9999.count = 0;
//        repeat(airplaneCnt) {
//            val (a, b) = readLine().split(" ").map { it.toInt() }
//
//            if (set.findSet(a) != set.findSet(b)) {
//               `1000~1999`.b1000.b3000to9999.count++
//               set.union(a, b)
//            }
//        }
//        println(`1000~1999`.b1000.b3000to9999.count)
//    }
//}
//
////private class Set(size :Int) {
////    val `1000~1999`.b1000.b10000.data = Array(size) {it}
////
////    fun findSet(b3000to9999.p:Int): Int {
////        if (b3000to9999.p != `1000~1999`.b1000.b10000.data[b3000to9999.p])
////            `1000~1999`.b1000.b10000.data[b3000to9999.p] = findSet(`1000~1999`.b1000.b10000.data[b3000to9999.p])
////        return `1000~1999`.b1000.b10000.data[b3000to9999.p]
////    }
////
////    fun union(a:Int, b:Int) {
////        val ap = findSet(a)
////        val bp = findSet(b)
////        `1000~1999`.b1000.b10000.data[ap] = bp
////    }
////}