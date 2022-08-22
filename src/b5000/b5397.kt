package test.b5000

fun main() = with(System.`in`.bufferedReader()) {
    val out = System.out.bufferedWriter()

    val repeat = readLine().toInt()
    repeat(repeat) {
        val left = mutableListOf<Char>()
        val right = mutableListOf<Char>()

        readLine().forEach {
            when(it) {
                '<' -> {
                    if (left.isNotEmpty())
                        right.add(left.removeLast())
                } '>' -> {
                    if (right.isNotEmpty())
                        left.add(right.removeLast())
                } '-' -> {
                    if (left.isNotEmpty())
                        left.removeLast()
                } else -> {
                    left.add(it)
                }
            }
        }

        left.forEach {
            out.append(it)
        }
        right.reversed().forEach {
            out.append(it)
        }
        out.newLine()
    }

    out.flush()
}