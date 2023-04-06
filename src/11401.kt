/*
페르마의 소정리
 */
fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val mod = 1000000007

    var res = 1L
    for (i in k + 1 .. n) // (n - r)!
        res = (res * i) % mod

    var base = 1L
    for (i in 1 .. n - k)
        base = (base * i) % mod

    var exponent = mod - 2
    while(exponent > 0) {
        if (exponent and 1 == 1)
            res = (res * base) % mod
        base = (base * base) % mod
        exponent = exponent shr 1
    }

    println(res)
}