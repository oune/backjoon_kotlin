package b2000

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    data class School(var boys:Int, var girls:Int)

    val schools = Array(3) {
        val (boys, girls) = readLine().split(' ').filter { it != "" }.map { it.toInt() }
        School(boys, girls)
    }

    for (i in 0..schools[0].boys) {
        val result = Array(3) {
            mutableListOf(0, 0)
        }
        result[0][0] = i;
        result[0][1] = schools[0].boys - i;
        result[1][1] = schools[2].girls - result[0][1];
        result[1][0] = schools[1].boys - result[1][1];
        result[2][0] = schools[0].girls - result[1][0];
        result[2][1] = schools[2].boys - result[2][0];

        val check = result.all { list ->
            list.all { it >= 0 }
        }

        if (check) {
            println('1')
            result.forEach {
                println("${it[0]} ${it[1]}")
            }
            return
        }
    }

    println('0')
}