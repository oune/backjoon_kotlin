/*
* 그리디
* 교환 횟수만큼 범위에서 최댓값을 앞으로 가져 올수 있음.
* 남아 있는 교환횟수만큼의 범위에서 최댓값을 찾고, 그 위치까지의 교환횟수를
* 남은 회수에서 뺌.
* 남은 리스트가 남거나, 회수를 다쓸때 까지 반봅
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
    while (numbs.size > 0 && cnt > 0) {
        var max = 0
        var idx = -1

        for (i in numbs.indices) {
            val now = numbs[i]
            if (now > max) {
                max = now
                idx = i
            }

            if (i == cnt)
                break
        }

        numbs.removeAt(idx)
        front.add(max)
        cnt -= idx
    }

    println((front + numbs).joinToString(" "))
}