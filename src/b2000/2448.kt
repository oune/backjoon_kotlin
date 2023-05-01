import java.lang.StringBuilder

fun main() {
    val n = readln().toInt()

    val ans = List(n) {
        StringBuilder()
    }

    fun printOne(start:Int, mid:Int, end:Int) {
        ans[start]  .append("  *  ")
        ans[mid]    .append(" * * ")
        ans[end]    .append("*****")
    }

    fun padding(padding:Int, range:IntRange) {
        for (i in range) {
            repeat(padding) {
                ans[i].append(' ')
            }
        }
    }

    fun print(start:Int,size:Int) {
        if (size == 3) {
            printOne(start, start + 1, start + 2)
            return
        }

        val half = start + size / 2
        padding(half, start until half)
        print(start,  size / 2)
        padding(half, start until half)

        print(half, size / 2)
        padding(1, half until size)
        print(half, size / 2)
    }

    print(0, n)

    print(ans.joinToString("\n") {
        it.toString()
    })
}