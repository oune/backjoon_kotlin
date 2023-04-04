fun main() {
    val text = readln()
    val pattern = readln()

    val pi = IntArray(pattern.length) { 0 }
    var j = 0
    for (i in 1..pattern.lastIndex) {
        while (j > 0 && pattern[i] != pattern[j])
            j = pi[j - 1]

        pi[i] = if (pattern[i] == pattern[j]) ++j else 0
    }

    val list = mutableListOf<Int>()
    j = 0
    for (i in text.indices) {
        while (j > 0 && text[i] != pattern[j])
            j = pi[j - 1]

        if (text[i] == pattern[j]) {
            if (j == pattern.lastIndex) {
                list.add(i - j + 1)
                j = pi[j]
            } else {
                j++
            }
        }
    }
    println(list.size)
    println(list.joinToString(" "))
}