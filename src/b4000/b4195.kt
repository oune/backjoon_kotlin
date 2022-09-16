package b4000

fun main() = with(System.`in`.bufferedReader()) {
    val out = System.out.bufferedWriter()

    val repeatCnt = readLine().toInt()
    repeat(repeatCnt) {
        val map = HashMap<String, String>()
        val size = HashMap<String, Int>()

        fun findSet(str:String): String {
            if (str != map[str]) {
                map[str] = findSet(map[str]!!)
            }
            return map[str]!!
        }
        fun isUnion(a:String, b:String):Boolean {
            return findSet(a) == findSet(b)
        }
        fun unionSet(a:String, b:String) {
            val pa = findSet(a)
            val pb = findSet(b)

            map[pa] = findSet(pb)
            size[pb] = size[pb]!!.plus(size[pa]!!)
        }

        val relations = Array(readLine().toInt()) {
            readLine().split(' ')
        }

        relations.forEach { (a, b) ->
            if (!map.containsKey(a)) {
                map[a] = a
                size[a] = 1
            }
            if (!map.containsKey(b)) {
                map[b] = b
                size[b] = 1
            }

            if (!isUnion(a, b)) {
                unionSet(a, b)
            }

            out.appendLine("${size[findSet(a)]}")
        }
    }
    out.flush()
}