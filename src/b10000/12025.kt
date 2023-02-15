fun main() = with(System.`in`.bufferedReader()) {
    val password = readLine().trim()
    val masking = readLine().toLong() - 1

    fun Long.isMasked(idx:Int): Boolean {
        return this and (1L shl idx) != 0L
    }
    val count = password.count {
        when (it) {
            '1', '2', '6', '7' -> true
            else -> false
        }
    }
    var idx = count - 1

    val sb = StringBuilder()
    for (char in password) {
        when(char) {
            '1', '6' -> {
                sb.append(if (masking.isMasked(idx--)) '6' else '1')
            }
            '2', '7' -> {
                sb.append(if (masking.isMasked(idx--)) '7' else '2')
            }
            else -> {
                sb.append(char)
            }
        }
    }

    val maximum = 1 shl count

    println(if (maximum < masking) -1 else if (masking < 0) -1 else sb)
}