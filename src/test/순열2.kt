package test.test

fun main() {
    val arr2 = intArrayOf(1, 5, 7)

    (0..2).forEach { i1 ->
        (0..2).forEach { i2 ->
            if (i1 != i2) {
                (0..2).forEach { i3 ->
                    if (i3 != i1 && i3 != i2) {
                        println("${arr2[i1]} ${arr2[i2]} ${arr2[i3]}")
                    }
                }
            }
        }
    }

    val size = 2
    val arr = intArrayOf(1, 7, 9, 10, 12)
    val selected = IntArray(size) { 0 }
    val visited = BooleanArray(arr.size) { false }

    fun perm(depth:Int, end:Int) {
        if (depth == end) {
            println(selected.contentToString())
            return
        }

        (arr.indices).forEach {
            if (!visited[it]) {
                selected[depth] = arr[it]
                visited[it] = true
                perm(depth + 1, end)
                visited[it] = false
            }
        }
    }

    perm(0, selected.size)
}