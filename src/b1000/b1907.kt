package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    data class Formula(var c:Int, var h:Int, var o:Int)

    fun formula(str: String) :Formula {
        val formula = Formula(0, 0, 0)

        for (i in str.indices) {
            when(str[i]) {
                'C', 'H', 'O' -> {
                    val count = if (i + 1 in str.indices && str[i + 1].isDigit()) str[i + 1].digitToInt() else 1

                    when(str[i]) {
                        'C' -> { formula.c += count }
                        'H' -> { formula.h += count }
                        'O' -> { formula.o += count }
                    }
                }
                else -> {
                    continue
                }
            }
        }
        return formula
    }

    val (a, b, c) = readLine().split('+', '=').map { formula(it) }

    for (i in 1..10) {
        for (j in 1..10) {
            for (k in 1..10) {

            }
        }
    }
}