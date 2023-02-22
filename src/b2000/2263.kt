fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val inorder = readLine().split(" ").map { it.toInt() }
    val postorder = readLine().split(" ").map { it.toInt() }

    val sb = StringBuilder()
    fun rec(inStart:Int, inEnd:Int, postStart:Int, postEnd:Int) {
        if(postStart>postEnd)
            return

        val root = postorder[postEnd]
        val rootIdx = inorder.indexOf(root) // 최적화 가능 부분

        sb.append("$root ")

        val leftSize = rootIdx - inStart
        rec(inStart, rootIdx - 1, postStart, postStart + leftSize - 1)
        rec(rootIdx + 1, inEnd, postStart + leftSize, postEnd - 1)
    }

    rec(0, inorder.lastIndex, 0, postorder.lastIndex)
    println(sb)
}