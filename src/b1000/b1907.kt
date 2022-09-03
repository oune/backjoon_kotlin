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

    fun Formula.plus(formula: Formula):Formula {
        return Formula(c + formula.c, h + formula.h, o + formula.o)
    }

    fun Formula.multifly(num: Int):Formula {
        return Formula(c * num, h * num, o * num)
    }

    val (a, b, c) = readLine().split('+', '=').map { formula(it) }
    var ans = Formula(-1, -1, -1)

    run {
        for (i in 1..10) {
            for (j in 1..10) {
                for (k in 1..10) {
                    val left = a.multifly(i).plus(b.multifly(j))
                    val right = c.multifly(k)

                    if (left == right) {
                        ans = Formula(i, j, k)
                        return@run
                    }
                }
            }
        }
    }

    println("${ans.c} ${ans.h} ${ans.o}")
}