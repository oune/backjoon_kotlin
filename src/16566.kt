/*
이분 탐색
가지고 있는 카드들 중 상대가 낸것중 가장 작은 카드를 내기
n, m = 4 * 10^6
서로소 집합을 이용하여 시간복잡도를 줄이는 테크닉이 필요함.

 */
fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val cards = readLine().split(" ").map { it.toInt() }.sorted()
    val indices = IntArray(cards.size) { it }

    fun findSet(idx:Int): Int {
        if (idx !in indices.indices)
            return findSet(0)
        if (indices[idx] != idx)
            indices[idx] = findSet(indices[idx])
        return indices[idx]
    }

    fun unionSet(a:Int, b:Int) {
        val pa = findSet(a)
        val pb = findSet(b)
        indices[pa] = pb
    }

    fun isUnion(a:Int, b:Int): Boolean {
        return findSet(a) == findSet(b)
    }

    fun upperbound(target: Int): Int {
        var left = 0
        var right = cards.lastIndex

        while(left <= right) {
            val mid = (left + right) / 2

            if (cards[mid] > target) {
                right = mid - 1
            } else if (cards[mid] < target) {
                left = mid + 1
            } else {
                left = mid + 1
                break
            }
        }

        return left
    }

    val res = readLine().split(" ").asSequence().map { it.toInt() }.map { target ->
        val idx = upperbound(target)
        val choice = findSet(idx)

        if (!isUnion(choice, choice + 1))
            unionSet(choice, choice + 1)

        cards[choice]
    }.joinToString("\n")

    println(res)
}