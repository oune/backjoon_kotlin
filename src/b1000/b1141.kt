package b1000

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with (BufferedReader(InputStreamReader(System.`in`))) {
    val wordCount = readLine().toInt()

    val words = mutableListOf<String>()
    repeat(wordCount) {
        words.add(readLine())
    }

    val sortedWords = words.sortedDescending()

    var pre = "INIT"
    val res = sortedWords.count {
        val regex = Regex("^$it.*")
        val now = pre
        pre = it

        !regex.matches(now)
    }
    print (res)
}