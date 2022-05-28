package test.line0528

import kotlin.math.roundToInt

fun main() {
    val logs = arrayOf("morgan sort", "felix sort", "morgan sqrt", "morgan sqrt", "rohan reverse", "rohan reverse")
    print(solution(logs).contentDeepToString())
}

fun solution(logs: Array<String>): Array<String> {
    val problemCount = HashMap<String, HashSet<String>>()
    val users = HashSet<String>()

    logs.forEach { log ->
        val (user, problem) = log.split(" ")

        if (problemCount.containsKey(problem)) {
            problemCount[problem]!!.add(user)
        } else {
            problemCount[problem] = hashSetOf(user)
        }

        users.add(user)
    }
    val userCnt = users.size
    println(userCnt)
    return problemCount.filter {
        val cnt = it.value.size
        cnt >= (userCnt/2.0).roundToInt()
    }.map {
        it.key
    }.sorted().toTypedArray()
}