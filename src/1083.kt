/*
* 그리디
* 교환 횟수만큼 범위에서 최댓값을 앞으로 가져 올수 있음.
*
* 반례
* ❌(1%)
10
1 2 3 4 5 6 7 8 9 10
17
answer:
10 9 1 2 3 4 5 6 7 8
*
* 런타임 에러 (NoSuchElement)(1%)
5
5 4 3 2 1
4
answer:  5 4 3 2 1
* 최댓값을 찾기 못했기에 에러가 발생
*
*
* */
fun main() {
    readln()
    val numbs = readln().split(" ").map { it.toInt() }.toMutableList()
    var cnt = readln().toInt()

    val front = mutableListOf<Int>()
    while (cnt > 0) {
        if (numbs.max() == numbs.first()) {
            val max = numbs.first()
            front.add(max)
            numbs.remove(max)
        }

        if (cnt >= numbs.size) {
            val max = numbs.max()
            val idx = numbs.indexOf(max)
            front.add(max)
            numbs.remove(max)
            cnt -= idx
        } else {
            val limit = cnt % numbs.size
            val max = numbs.slice(0..limit).max()
            val idx = numbs.indexOf(max)
            front.add(max)
            numbs.remove(max)
            cnt -= idx
        }
    }

//    println(front.joinToString(" "))
//    println(numbs.joinToString(" "))
    println((front + numbs).joinToString(" "))
}