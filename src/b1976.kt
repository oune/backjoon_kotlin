//fun main() = with(System.`in`.bufferedReader()) {
//    val cityCnt = readLine().toInt()
//    val set = Set(cityCnt + 1)
//    val tripCityCnt = readLine()
//
//    repeat(cityCnt) { i ->
//        readLine().split(" ").forEachIndexed { index, s ->
//            if (s == "1") {
//                set.union(i + 1, index + 1)
//            }
//        }
//    }
//
//    val tripList = readLine().split(" ").map{ it.toInt() }
//    val parent = set.findSet(tripList[0])
//    val res = tripList.all {
//        set.findSet(it) == parent
//    }
//    if (res) {
//        print("YES")
//    } else {
//        print("NO")
//    }
//}
//class Set (size: Int) {
//    private val data = Array(size) { it }
//
//    fun findSet(p: Int): Int {
//        if (p != data[p])
//            data[p] = findSet(data[p])
//        return data[p]
//    }
//
//    fun union(a: Int, b: Int) {
//        val ap = findSet(a)
//        val bp = findSet(b)
//        data[ap] = bp
//    }
//}
