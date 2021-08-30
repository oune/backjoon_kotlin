package b1000//fun b1000.b3000to9999.b10000.main() = with(System.`in`.bufferedReader()) {
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
//    private val `1000~1999`.b1000.b10000.data = Array(size) { it }
//
//    fun findSet(b3000to9999.p: Int): Int {
//        if (b3000to9999.p != `1000~1999`.b1000.b10000.data[b3000to9999.p])
//            `1000~1999`.b1000.b10000.data[b3000to9999.p] = findSet(`1000~1999`.b1000.b10000.data[b3000to9999.p])
//        return `1000~1999`.b1000.b10000.data[b3000to9999.p]
//    }
//
//    fun union(a: Int, b: Int) {
//        val ap = findSet(a)
//        val bp = findSet(b)
//        `1000~1999`.b1000.b10000.data[ap] = bp
//    }
//}
