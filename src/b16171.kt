fun main() = with(System.`in`.bufferedReader()) {
    val book = readLine()
    val text = readLine()
    print(if (book.filter { it < '0' || it > '9' }.contains(text)) { "1" } else { "0" })
}