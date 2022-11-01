package b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (l, c) = readLine().split(' ').map { it.toInt() }
    val chars = readLine().split(' ').sorted()
    val visit = Array(chars.size){ false }

    fun Char.isVowel() :Boolean {
        return when(this) {
            'a', 'i', 'u', 'e', 'o' -> true
            else -> false
        }
    }

    fun Char.isConsonant() :Boolean {
        return when(this) {
            'a', 'i', 'u', 'e', 'o' -> false
            else -> true
        }
    }

    fun subSet(depth:Int, cnt:Int) {
        if (depth == c) {
            if (cnt == l) {
                var str = ""
                for (i in 0 until c) {
                    if (visit[i]) {
                        str += chars[i]
                    }
                }
                val vowelCount = str.count { it.isVowel() }
                if (vowelCount == 0)
                    return

                val consonantCount = str.count{ it.isConsonant() }
                if (consonantCount < 2)
                    return

                println(str)
            }
            return
        }

        visit[depth] = true
        subSet(depth + 1, cnt + 1)
        visit[depth] = false
        subSet(depth + 1, cnt)
    }

    subSet(0, 0)
}