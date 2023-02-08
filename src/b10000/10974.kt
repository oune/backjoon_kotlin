fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()
    val nums = IntArray(num) {
        it + 1
    }
    val sb = StringBuilder()
    val visited = BooleanArray(nums.size) { false }
    val ans = IntArray(nums.size) { 0 }
    fun perm(depth:Int) {
        if (depth == nums.size) {
            sb.appendLine(ans.joinToString(" "))
            return
        }

        for (i in nums.indices) {
            if (!visited[i]) {
                ans[depth] = nums[i]
                visited[i] = true
                perm(depth + 1)
                visited[i] = false
            }
        }
    }

    perm(0)
    println(sb)
}