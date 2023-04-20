fun main() {
    val testcase = readln().toInt()

    val ans = List(testcase) {
        var money = readln().toInt()

        val divides = listOf(25, 10, 5, 1)
        val res = divides.map { divide ->
            val count = money / divide
            money %= divide
            count
        }

        res.joinToString(" ")
    }

    println(ans.joinToString("\n"))
}