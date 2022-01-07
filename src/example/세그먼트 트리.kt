package test.example

fun main() {
    val (n, a, b) = readLine()!!.split(" ").map { it.toInt() }
    var start = 1
    while(start < n) {
        start *= 2
    }
    val treeSize = 2 * start - 1
    val arr = IntArray(treeSize){0}
    repeat(n) {
        setValue(it, readLine()!!.toInt(), start, arr)
    }


    print(arr.contentToString())
}

fun setValue(idx:Int, v:Int, start:Int, arr:IntArray) {
    var index = idx + start
    val value = v - arr[idx]

    while (index != 0) {
        arr[index] += value
        index /= 2
    }
}