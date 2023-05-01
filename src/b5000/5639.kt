fun main() {
    data class Node(var left:Node?, var right:Node?, val data:Int)

    fun Node.set(value:Int) {
        if (value < data)
            if (left == null)
                left = Node(null, null, value)
            else
                left!!.set(value)
        if (value > data)
            if (right == null)
                right = Node(null, null, value)
            else
                right!!.set(value)
    }

    var root : Node? = null
    while(true) {
        val num = readlnOrNull()?.toInt() ?: break

        if (root == null) {
            root = Node(null, null, num)
        } else {
            root.set(num)
        }
    }

    fun Node.postOrder():List<Int> {
        return (left?.postOrder() ?: listOf()) + (right?.postOrder() ?: listOf()) + listOf(data)
    }

    if (root != null)
        print(root.postOrder().joinToString("\n"))
}