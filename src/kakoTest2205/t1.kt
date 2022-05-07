package test.kakoTest2205

import com.sun.xml.internal.fastinfoset.util.StringArray

fun main() {
    val survey = arrayOf("AN", "CF", "MJ", "RT", "NA")
    val choices = arrayOf(5, 3, 2, 7, 5).toIntArray()

    print(solution(survey, choices))
}

fun solution(survey: Array<String>, choices: IntArray): String {
    val scores = Array(4){0}

    for (i in survey.indices) {
        val now = survey[i]
        val choice = choices[i]

        // "RT", "TR", "FC", "CF", "MJ", "JM", "AN", "NA"
        when (now) {
            "RT" -> {
                scores[0] += 4 - choice
            }
            "TR" -> {
                scores[0] += choice - 4
            }
            "CF" -> {
                scores[1] += 4 - choice
            }
            "FC" -> {
                scores[1] += choice - 4
            }
            "JM" -> {
                scores[2] += 4 - choice
            }
            "MJ" -> {
                scores[2] += choice - 4
            }
            "AN" -> {
                scores[3] += 4 - choice
            }
            "NA" -> {
                scores[3] += choice - 4
            }
        }
    }

    var answer: String = ""

    if (scores[0] >= 0) {
        answer += "R"
    } else {
        answer += "T"
    }

    if (scores[1] >= 0) {
        answer += "C"
    } else {
        answer += "F"
    }

    if (scores[2] >= 0) { // 조건 위험
        answer += "J"
    } else {
        answer += "M"
    }

    if (scores[3] >= 0) {
        answer += "A"
    } else {
        answer += "N"
    }

    return answer
}