fun main() {
    val ans = mutableListOf<String>()
    while(true) {
        val (s, t) = readlnOrNull()?.split(" ") ?: break

        var idx = 0
        for (char in t) {
            if (char == s[idx])
                idx++
            if (idx !in s.indices)
                break
        }

        ans.add(if (idx == s.length) "Yes" else "No")
    }

    print(ans.joinToString("\n"))
}