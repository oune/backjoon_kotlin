fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    fun isNotAntic(char:Char):Boolean {
        return when(char) {
            'a' -> false
            'n' -> false
            't' -> false
            'i' -> false
            'c' -> false
            else -> true
        }
    }

    val words = List(n) {
        readLine().filter { isNotAntic(it) }
    }

    val chars = List('z' - 'a' + 1) {
        (it + 'a'.code).toChar()
    }.filter { isNotAntic(it) }

    fun Int.isVisited(index:Int): Boolean {
        return this and (1 shl index) != 0
    }

    if (k < 5) {
        println(0)
    } else if (k == 26) {
        print(n)
    } else {
        var max = 0
        for (masking in 0 until(1 shl chars.size)) {
            val list = chars.filterIndexed { index, _ -> masking.isVisited(index) }

            if (list.size == k - 5) {
                var count = words.count { word ->
                    word.all { it in list }
                }

                if (max < count)
                    max = count
            }
        }

        println(max)
    }
}