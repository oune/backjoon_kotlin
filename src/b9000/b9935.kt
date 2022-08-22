package test.b9000

fun main() = with(System.`in`.bufferedReader()) {
    val line = readLine()
    val bomb = readLine()
    val stack = mutableListOf<Char>()

    line.forEach {
        stack.add(it)

        if (stack.size >= bomb.length) {
            var isBomb = true
            bomb.forEachIndexed { idx, char ->
                if (char != stack[stack.lastIndex - bomb.lastIndex + idx])
                    isBomb = false
            }
            if (isBomb)
                repeat(bomb.length) {
                    stack.removeLast()
                }
        }
    }

    val out = System.out.bufferedWriter()
    if (stack.isNotEmpty()) {
        stack.forEach {
            out.append(it)
        }
    } else {
        out.append("FRULA")
    }
    out.flush()
}