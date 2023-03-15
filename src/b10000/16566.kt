/*
이분 탐색
가지고 있는 카드들 중 상대가 낸것중 가장 작은 카드를 내기
n, m = 4 * 10^6
서로소 집합을 이용하여 시간복잡도를 줄이는 테크닉이 필요함.

 */

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val cards = readLine().split(" ").map { it.toInt() }.toIntArray()
    val round = readLine().split(" ").map { it.toInt() }.toIntArray()
    cards.sort()

    val indices = IntArray(cards.size) { it }

    val sb = StringBuilder()
    for (target in round) {
        val idx = cards.upperbound(target)
        val choice = indices.find(idx)
        sb.appendLine(cards[choice])
    }
    print(sb)
}

fun IntArray.find(idx:Int): Int {
    if (idx == this[idx]) {
        var nextIdx = idx + 1

        while( nextIdx in this.indices && nextIdx != this[nextIdx]) {
            nextIdx++
        }

        this[idx] = nextIdx
        return idx
    } else {
        this[idx] = find(this[idx])
        return this[idx]
    }
}

fun IntArray.upperbound(target: Int): Int {
    var left = 0
    var right = this.lastIndex
    var ans = 0

    while (left <= right){
        val mid = (left + right) / 2

        if (this[mid] > target){
            ans = mid
            right = mid - 1
        }
        else
            left = mid + 1
    }

    return ans
}