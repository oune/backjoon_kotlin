package b1000//fun b1000.b3000to9999.b10000.main() = with(System.`in`.bufferedReader()) {
//    val (v, e) = readLine().split(" ").map { it.toInt() }
//
//    val edgeList = ArrayList<Triple<Int, Int, Int>>()
//    repeat(e) {
//        val (a, b, c) = readLine().split(" ").map { it.toInt() }
//        edgeList.add(Triple(a, b, c))
//    }
//
//    var total = 0
//    val set = Set(v + 1)
//    edgeList.sortedBy { it.third }.forEach {
//        if (set.findSet(it.first) != set.findSet(it.second)) {
//            set.union(it.first, it.second)
//            total += it.third
//        }
//    }
//
//    print(total)
//}
//
//class Set(size:Int) {
//    val `1000~1999`.b1000.b10000.data = Array(size) { it }
//
//    fun findSet(b3000to9999.p: Int) :Int{
//        if (b3000to9999.p != `1000~1999`.b1000.b10000.data[b3000to9999.p])
//            `1000~1999`.b1000.b10000.data[b3000to9999.p] = findSet(`1000~1999`.b1000.b10000.data[b3000to9999.p])
//        return `1000~1999`.b1000.b10000.data[b3000to9999.p]
//    }
//
//    fun union(a:Int, b:Int) {
//        val ap = findSet(a)
//        val bp = findSet(b)
//        `1000~1999`.b1000.b10000.data[ap] = bp
//    }
//}