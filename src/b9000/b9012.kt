package test.b9000

fun main() = with(System.`in`.bufferedReader()){
    val out = System.out.bufferedWriter()

    val repeat = readLine().toInt()
    repeat(repeat) {
        val stack = mutableListOf<Char>()
        var isVPS = true

        readLine().forEach {
            if (it == '(')
                stack.add('(')
            if (it == ')')
                if (stack.isNotEmpty())
                    stack.removeLast()
                else
                    isVPS = false
        }

        if (stack.isNotEmpty())
            isVPS = false

        out.appendLine(if (isVPS) "YES" else "NO")
    }

    out.flush()
}