package b1000
import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val nodeCount = readLine().toInt()

    data class Node(val name:String, var left: Node?, var right: Node?)

    fun Node.find(name: String):Node {
        val que = LinkedList<Node>()
        que.offer(this)

        while (que.isNotEmpty()) {
            val now = que.poll()

            if (now.name == name)
                return now

            now.left?.let {
                que.offer(it)
            }
            now.right?.let {
                que.offer(it)
            }
        }

        return Node("NOT_FOUND", null, null)
    }

    fun Node.insert(node: Node) {
        val targetName = node.name
        val target = this.find(targetName)

        target.left = node.left
        target.right = node.right
    }

    fun Node.postOrder(): String {
        return this.name + (this.left?.postOrder() ?: "") + (this.right?.postOrder() ?: "")
    }

    fun Node.inOrder(): String {
        return (this.left?.inOrder() ?: "") + this.name + (this.right?.inOrder() ?: "")
    }

    fun Node.preOrder(): String {
        return (this.left?.preOrder() ?: "") + (this.right?.preOrder() ?: "") + this.name
    }

    fun makeNode(name: String, left: String, right: String): Node {
        val leftNode = if (left == ".") null else Node(left, null, null)
        val rightNode = if (right == ".") null else Node(right, null, null)

        return Node(name, leftNode, rightNode)
    }

    val (a, b, c) = readLine().split(" ")
    val tree = makeNode(a, b, c)

    repeat(nodeCount - 1) {
        val (name, left, right) = readLine().split(" ")
        val now = makeNode(name, left, right)

        tree.insert(now)
    }

    println(tree.postOrder())
    println(tree.inOrder())
    println(tree.preOrder())
}