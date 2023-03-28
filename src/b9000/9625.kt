/*
dp
점화식까지 알려주는 친절한 문제
 */
fun main() {
    val k = readln().toInt()

    val a = IntArray(k + 1) { 1 }
    val b = IntArray(k + 1) { 0 }

    for (i in 1..k) {
        a[i] = b[i - 1];
        b[i] = a[i - 1] + b[i - 1]
    }

    println("${a.last()} ${b.last()}")
}