package b1000

fun main() {
    val input = readLine()

    val len = input!!.length
    val words = mutableListOf<String>()
    (1 until len).forEach { i ->
        (i + 1 until len).forEach { j ->
            words += input.substring(0, i).reversed() + input.substring(i, j).reversed() + input.substring(j, len).reversed()
        }
    }
    println(words.minOrNull())
}