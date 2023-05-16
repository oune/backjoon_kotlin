fun main() {
    val s = readln()
    var t = readln()

    while (t.length > s.length) {
        t = when(t.last()) {
            'A' -> { t.substring(0 until t.lastIndex) }
            'B' -> { t.substring(0 until t.lastIndex).reversed() }
            else -> { break }
        }
    }
    println( if (s == t) 1 else 0 )
}