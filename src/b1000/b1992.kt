package b1000

fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()
    val arr = Array(num) {
        readLine().toCharArray()
    }

    print(rec(arr, num - 1, num - 1, num))
}

fun rec(arr:Array<CharArray>, i:Int, j:Int, size:Int):String {
    if (size == 1)
        return arr[i][j].toString()

    val newSize = size / 2
    val res = listOf(
        rec(arr, i - newSize, j - newSize, newSize),
        rec(arr, i - newSize, j, newSize),
        rec(arr, i, j - newSize, newSize),
        rec(arr, i, j, newSize)
    ).fold("") {
        acc, s ->
        acc + s
    }
    return if (res.all { it == '0' }) {
        "0"
    } else if (res.all { it == '1' }) {
        "1"
    } else {
        "($res)"
    }
}