import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    readLine()

    data class Tower(val index:Int, val height:Int)
    val towers = readLine().split(" ").mapIndexed { index, s -> Tower(index + 1, s.toInt()) }

    val ans = mutableListOf<Int>()
    val stack = mutableListOf<Tower>()
    for (tower in towers) {
        while (stack.isNotEmpty() && stack.last().height <= tower.height) {
            stack.removeLast()
        }

        if (stack.isEmpty()) {
            ans.add(0)
        } else {
            ans.add(stack.last().index)
        }

        stack.add(tower)
    }

    println(ans.joinToString(" "))
}