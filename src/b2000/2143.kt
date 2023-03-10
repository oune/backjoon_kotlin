/*
부분합
연속된 순열의 합을 구하는 문제이기 때문에 부분합을 이용.
해당 부분합의 일부 연속된 부분의 합을 구하기 위해서
투포인트 비슷한 느낌으로 조합을 이용해서 합을 구함.
또한 해당합의 개수를 해시맵을 이용하여 저장하고
나중에 목표 합에 맞는 값의 카운트와 나의 카운트를 이용하여 전체 개수를 계산함.

틀림
타입 문제
 */
fun main() = with(System.`in`.bufferedReader()) {
    val target = readLine().toInt()
    readLine()
    val a = readLine().split(" ").filter { it != "" }.map { it.toInt() }
    readLine()
    val b = readLine().split(" ").filter { it != "" }.map { it.toInt() }

    fun getCounts(list:List<Int>):HashMap<Int, Int> {
        val acc = list.scan(0) { acc, i -> acc + i }
        val map = HashMap<Int, Int>()

        for (from in acc.indices) {
            for (to in from + 1 .. acc.lastIndex) {
                val sum = acc[to] - acc[from]
                if (!map.containsKey(sum))
                    map[sum] = 0

                map[sum] = map[sum]!! + 1
            }
        }

        return map
    }

    val countA = getCounts(a)
    val countB = getCounts(b)

    var count = 0L
    for (cA in countA) {
        val key = target - cA.key
        if (countB.containsKey(key)) {
            count += cA.value.toLong() * countB[key]!!.toLong()
        }
    }
    println(count)
}