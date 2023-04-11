import java.io.BufferedReader
import java.io.InputStreamReader


fun main() {
    val text = readln().toCharArray()
    val pattern = readln().toCharArray()

    // 부분일치테이블 만들기 : KMP의 아이디어를 똑같이 적용, W에서 W를 찾는 듯한 행위를 해서...
    val pi = IntArray(pattern.size) { 0 }
    var j = 0
    for (i in 1 .. pattern.lastIndex) {
        while (j > 0 && pattern[i] != pattern[j])
            j = pi[j - 1]

        pi[i] = if (pattern[i] == pattern[j]) ++j else 0
    }

    val list = ArrayList<Int>()
    // i : 텍스트 포인터 , j: 패턴 포인터
    j = 0
    for (i in text.indices) {
        while (j > 0 && text[i] != pattern[j])
            j = pi[j - 1]

        if (text[i] == pattern[j]) { //두 글자 일치
            if (j == pattern.lastIndex) { // j가 패턴의 마지막 인덱스라면
                list.add(i - j)
                j = pi[j]
            } else {
                j++
            }
        }
    }

    println(list)
}