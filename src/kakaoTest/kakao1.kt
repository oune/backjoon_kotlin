package test.kakaoTest

fun main() {
    for (i in 1 .. 1000000) {
        for (j in 3..10) {
            solution(i,j)
        }
    }
}
fun solution(n: Int, k: Int): Int {
    val isPrime = BooleanArray(1000001) { true }
    isPrime[0] = false
    isPrime[1] = false
    for (i in 2 .. isPrime.size / 2) {
        if (isPrime[i]) {
            var idx = i + i
            while (idx < isPrime.size ) {
                isPrime[idx] = false
                idx += i
            }
        }
    }

    var res = "2211111111"
    var num = n
    while (num > 0) {
        res = (num % k).toString() + res
        num /= k
    }

    val answer = res.split("0").filter{
        it != ""
    }.map{
        it.toInt()
    }.count{
        if (it < isPrime.size)
            isPrime[it]
        else
            false
    }

    return answer
}