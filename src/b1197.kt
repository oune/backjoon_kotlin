//fun main() = with(System.`in`.bufferedReader()) {
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
//    val data = Array(size) { it }
//
//    fun findSet(p: Int) :Int{
//        if (p != data[p])
//            data[p] = findSet(data[p])
//        return data[p]
//    }
//
//    fun union(a:Int, b:Int) {
//        val ap = findSet(a)
//        val bp = findSet(b)
//        data[ap] = bp
//    }
//}