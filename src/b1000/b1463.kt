import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()
    print(f(num, 0))
}
private val arr = MutableList(10.toDouble().pow(6).toInt() + 1){ -1 }
fun rec(num:Int) :Int {
    if ( num == 1 )
        return 0
    if (arr[num] != -1)
        return arr[num]

    var v1 = rec(num - 1)

    if (num % 3 == 0) {
        val v2 = rec(num / 3)
        if ( v1 > v2 )
            v1 = v2
    }

    if (num % 2 == 0) {
        val v2 = rec(num / 2)
        if ( v1 > v2 )
            v1 = v2
    }

    arr[num] = v1 + 1
    return arr[num]
}

private tailrec fun f(num: Int, acc:Int) :Int {
    if (num == 1) {
        return acc
    }
    if (num % 3 == 0) {
        return f (num / 3, acc + 1)
    }

    if (num % 2 == 0) {
        return f (num / 2, acc + 1)
    }

    return f(num - 1, acc + 1)
}