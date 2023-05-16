/*
* 그리디
* k개의 조를 만들기 == k-1 개의 관계를 제거하기
* */
fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val numbs = readln().split(" ").map { it.toInt() }

    val distance = mutableListOf<Int>()
    var pre = numbs.first()
    for (numb in numbs.drop(1)) {
        distance += numb - pre
        pre = numb
    }

    println(distance.sortedDescending().drop(k - 1).sum())
}