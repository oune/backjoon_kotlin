package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    val testcases = readLine().toInt()

    data class Score(val paper:Int, val meeting:Int)

    val out = System.out.bufferedWriter()
    repeat(testcases) {
        val applicantCnt = readLine().toInt()
        val applicants = Array(applicantCnt) {
            val (paper, meeting) = readLine().split(" ").map { it.toInt() }

            Score(paper, meeting)
        }
        
        val sorted = applicants.sortedWith(
            compareBy<Score> { it.paper }.thenBy {
                it.meeting
            }
        )
        
        var cutline = Int.MAX_VALUE
        var count = 0
        sorted.forEach {
            if (cutline > it.meeting) {
                cutline = it.meeting
                count++
            }
        }
        out.appendLine(count.toString())
    }
    out.flush()
}