package test.b4000

fun main() = with(System.`in`.bufferedReader()) {
    val out = System.out.bufferedWriter()

    val repeatCnt = readLine().toInt()
    repeat(repeatCnt) {
        val map = HashMap<String, String>()

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
            map[findSet(a)] = findSet(b)
        }

        val relations = Array(readLine().toInt()) {
            readLine().split(' ')
        }

        relations.forEach { (a, b) ->
            if (!map.containsKey(a)) {
                map[a] = a
            }
            if (!map.containsKey(b)) {
                map[b] = b
            }

            if (!isUnion(a, b)) {
                unionSet(a, b)
            }

            val count = map.count { (_, value) ->
                value == findSet(a) || value == findSet(b) || value == a || value == b
            }

            out.appendLine("$count")
        }
    }
    out.flush()
}