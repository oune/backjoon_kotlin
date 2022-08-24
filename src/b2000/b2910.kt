package test.b2000

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val messages = readLine().split(" ").map { it.toInt() }

    data class Node(var order: Int, var count:Int )

    val map = HashMap<Int, Node>()

    var order = 1
    messages.forEach { message ->
        if (!map.containsKey(message))
            map[message] = Node(order++, 1)
        else {
            map[message]!!.count++
        }
    }

    val out = System.out.bufferedWriter()

    map.toList().sortedWith(
        compareBy<Pair<Int, Node>> {
            -it.second.count
        }.thenBy {
            it.second.order
        }
    ).forEach { pair ->
        repeat(pair.second.count) {
            out.append("${pair.first } ")
        }
    }
    out.flush()
}