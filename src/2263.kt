fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val inorder = readLine().split(" ").map { it.toInt() }
    val postorder = readLine().split(" ").map { it.toInt() }

    data class Node(var left:Int, var right:Int)

    val map = List(size + 1) {
        Node(0, 0)
    }

    fun rec(inorder:List<Int>, postorder:List<Int>):Int {
        if (inorder.size != postorder.size)
            throw RuntimeException("orders size not same")

        val root = postorder.last()

        if (postorder.size < 2)
            return root

        val mid = inorder.indexOf(root)
        val inorderLeft = inorder.slice(0 until mid)
        val inorderRight = inorder.slice(mid + 1 .. inorder.lastIndex)

        val postorderLeft = mutableListOf<Int>()
        val postorderRight = mutableListOf<Int>()
        for (num in postorder.dropLast(1)) {
            when(num) {
                in inorderLeft -> {
                    postorderLeft += num
                }
                in inorderRight -> {
                    postorderRight += num
                }
            }
        }

        if (inorderLeft.isNotEmpty())
            map[root].left = rec(inorderLeft, postorderLeft)
        if (inorderRight.isNotEmpty())
            map[root].right = rec(inorderRight, postorderRight)

        return root
    }

    val root = rec(inorder, postorder)

    fun preorder(num:Int):List<Int> {
        val list = mutableListOf(num)

        val left = map[num].left
        if (left != 0)
            list += preorder(left)

        val right = map[num].right
        if (right != 0)
            list += preorder(right)

        return list
    }

    val res = preorder(root).joinToString(" ")

    println(res)
}