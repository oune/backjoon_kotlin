fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val words = List(n) {
        readLine().filter { when(it) {
            'a' -> false
            'n' -> false
            't' -> false
            'i' -> false
            'c' -> false
            else -> true
        }
        }.toList()
    }

    val chars = List('z' - 'a' + 1) {
        (it + 'a'.code).toChar()
    }.filter { when(it) {
        'a' -> false
        'n' -> false
        't' -> false
        'i' -> false
        'c' -> false
        else -> true
        }
    }

    fun Int.isVisited(index:Int): Boolean {
        return this and (1 shl index) != 0
    }

    fun List<Char>.subset(): List<List<Char>> {
        val res = mutableListOf<List<Char>>()

        for (masking in 1 until(1 shl this.size)) {
            val list = this.filterIndexed { index, _ -> masking.isVisited(index) }
            res.add(list)
        }

        return res
    }

    if (k < 5) {
        println(0)
    } else if (k == 26) {
        print(n)
    } else {
        val subsets = chars.subset().filter { it.size == k - 5}

        val ans = subsets.maxOf { selected ->
            words.count { word ->
                word.all { it in selected}
            }
        }

        println(ans)
    }
}