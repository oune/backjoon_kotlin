package b2000

fun main() = with(System.`in`.bufferedReader()) {
    val totalStudents = readLine().toInt()
    data class School(var boys:Int, var girls:Int)

    val schools = Array(3) {
        val (boys, girls) = readLine().split(' ').filter { it != "" }.map { it.toInt() }
        School(boys, girls)
    }
    val res = Array(3) {
        mutableListOf(0, 0)
    }

    schools.forEachIndexed { i, school ->
        val targets = schools.filter { it !== school }
        val first = targets[0]
        val second = targets[1]

        while (school.boys > 0) {
            if (first.girls > 0) {
                school.boys--
                first.girls--
                res[i][0]++
            } else if (second.girls > 0) {
                school.boys--
                second.girls--
                res[i][1]++
            } else {
                res[i][0] = -40000000
                res[i][1] = -40000000
            }
        }
    }

    val sum = res.sumOf { it.sum() }

    if (sum == totalStudents) {
        println('1')
        res.forEach { list ->
            println("${list[0]} ${list[1]}")
        }
    } else {
        println('0')
    }
}