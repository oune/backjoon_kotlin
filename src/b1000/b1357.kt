package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    fun rev(num:Int) :Int{
        var res = 0
        var n = num
        while(n > 0) {
            res *= 10
            res += n % 10
            n /= 10
        }
        return res
    }

    val (x, y) = readLine().split(' ').map { it.toInt() }
    println(rev(rev(x) + rev(y)))
}