/*
kmp
문자열중 접두사랑 접미사를 확인하여 가장 긴 길이를 확인
이를 kmp알고리즘에서 부분일치 문자열 테이블을 만드는것과 동일
 */
fun main() {
    readln()
    val text = readln().toCharArray()

    val pi = IntArray(text.size) { 0 }
    var j = 0
    for (i in 1 .. text.lastIndex) {
        while (j > 0 && text[i] != text[j])
            j = pi[j - 1]

        pi[i] = if (text[i] == text[j]) ++j else 0
    }

    println(pi.joinToString(" "))
    println(text.size - pi[text.lastIndex])
}