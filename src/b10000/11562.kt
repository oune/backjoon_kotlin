import kotlin.math.min

/*
플로이드 워샬
일방통행길을 양방통행으로 바꾸는 횟수를 출력
플롲이드 워샬을 이용하여 최단 거리를 구함
단, 처음에 비용을 계산할때
길이 뚫려 있는 경우에는 0, 길이 없는 경우에는 1로 설정해서
길이 없으면 양방 통행으로 바꾸었다고 생각하도록 설정
이렇게 되면 길을 적게 바꾼 횟수를 알 수 있음.
* */
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val maxValue = n * n * 2

    val map = List(n) {
        IntArray(n) { maxValue }
    }

    repeat(m) {
        val (from, to, type) = readln().split(" ")
            .map { it.toInt() }
            .mapIndexed { index, i ->
                if (index < 2)
                    i - 1
                else i
            }

        map[from][to] = 0
        map[to][from] = if (type == 1) 0 else 1
    }

    for (via in map.indices) {
        for (from in map.indices) {
            if (from == via)
                continue

            for (to in map.indices) {
                if (via == to)
                    continue
                if (from == to) {
                    map[from][to] = 0
                    continue
                }


                map[from][to] = min(map[from][to], map[from][via] + map[via][to])
            }
        }
    }

    val queryCnt = readln().toInt()
    val ans = List(queryCnt) {
        val (from, to) = readln().split(" ").map { it.toInt() - 1 }
        map[from][to]
    }

    println(ans.joinToString("\n"))
}