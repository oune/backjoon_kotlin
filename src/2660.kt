import kotlin.math.max
import kotlin.math.min

/*
플로이드 워샬
다른 회원과 모두 친구면 1
친구의 친구면 2
친구의 친구의 친구면 3
자신의 친구들간의 친구 거리의 최댓값이 점수
점수가 가장 낮은 사람이 당선
따라서 모든 회원간의 최단거리를 확인해야 하므로
플로이드 워샬을 사용하는 것이 합리적
또한 n=50 이므로 , 1.25 * 10^5
1초 이내 가능
 */

fun main() {
    val userCnt = readln().toInt()

    val maxValue = userCnt * userCnt + 10
    val map = List(userCnt + 1) {
        IntArray(userCnt + 1) { maxValue }
    }

    while(true) {
        val (userA, userB) = readln().split(" ").map { it.toInt() }

        if (userA == -1 && userB == -1)
            break

        map[userA][userB] = 1
        map[userB][userA] = 1
    }

    for (via in map.indices) {
        for (from in map.indices) {
            if (via == from)
                continue

            for (to in map.indices) {
                if (via == to)
                    continue
                if (from == to)
                    continue

                map[from][to] = min((map[from][via] + map[via][to]), map[from][to])
            }
        }
    }

    val candidates = mutableListOf<Int>()
    var min = maxValue

    for (i in map.indices) {
        val friends = map[i]
        val score = friends.filter { it != maxValue }.maxOrNull() ?: maxValue

        if (score > min)
            continue
        if (score < min)
            candidates.clear()

        min = min(min, score)
        candidates.add(i)
    }

    println("$min ${candidates.size}")
    println(candidates.joinToString(" "))
}