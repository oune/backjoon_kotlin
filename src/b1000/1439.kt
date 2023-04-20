fun main() {
    val str = readln()

    val first = str.first()
    var pre = first
    var count = 0
    for (char in str.drop(1)) {
        if (pre == first && char != first)
            count++

        pre = char
    }

    println(count)
}