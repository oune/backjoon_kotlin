package test
//
//fun main() {
//    val set = Set()
//    set.print()
//    print(set.findSet(2))
//}
//
//private class Set {
//    private val data = Array(10) { it }
//
//    fun print() {
//        println(data.contentDeepToString())
//    }
//
//    fun findSet(p: Int) :Int{
//        if (p == data[p])
//            return p
//        return findSet(data[p])
//    }
//
//    fun unionSet(a: Int, b: Int) {
//        val aPrent = findSet(a)
//        val bPrent = findSet(b)
//        data[aPrent] = bPrent
//    }
//}