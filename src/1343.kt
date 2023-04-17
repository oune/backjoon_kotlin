fun main() {
    val str = readln().toCharArray()

    for (i in 1 .. str.lastIndex) {
        if (str[i] == 'X') {
            if (str[i - 1] == 'X') {
                if (i - 2 in str.indices && str[i - 2] == 'B') {
                    for (j in 0..3) {
                        str[i - j] = 'A'
                    }
                } else {
                    for (j in 0..1) {
                        str[i - j] = 'B'
                    }
                }
            }
        }
    }
    println(if (str.any { it == 'X'} ) "-1" else str.joinToString(""))
}