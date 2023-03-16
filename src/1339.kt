import kotlin.math.pow

/*
그리디
n개의 단어를 숫자로 변환하였을때 수들의 합이 최대가 되도록
자리수 마다 가중치를 부여해서 저장
가중치가 가장 높은 수부터 9를 부여하여 1까지 부여

강의 들으면서 푸니까 50분 걸림
 */
fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val arr = IntArray('Z' - 'A' + 1) { 0 }
    repeat(size) {
        val word = readLine()

        for (i in word.indices.reversed()) {
            val depth = word.length - 1 - i;
            val cost = 10.0.pow(depth.toDouble()).toInt()
            val idx = word[i] - 'A'

            arr[idx] += cost
        }
    }

    val sum = arr.sortedBy { -it }
        .slice(0..8)
        .mapIndexed { index, i ->
            i * (9 - index)
        }.sum()

    println(sum)
}