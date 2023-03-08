import java.util.LinkedList
/*
단순 구현, 시뮬레이션
타석의 전체 개수 9개
순열의 개수 = 9! = 362,880
이닝의 수 50
한 이닝에서 최대 도는 타석의 수 = 3 * 9
전체 경우의 수 = 362,880 * 50 * 27 = 489,888,000 = 10^8.6
>> 간당간당한 시간복잡도.

게임을 몇 이닝동안 하는지 확인 하는 부분이 조금 헷갈렸음.

시간 초과 발생
점수를 낼때 리스트를 조작하면서 넣고 빼면서 진행했는데 발생
자료구조가 필요하지 않은 부분까지 자료구조를 이용하여 구현하면 시간초과 최대한 간단화 시키는 부분이 필요했음.
배열의 drop 이후 sum 하는 방식으로 구현하니까 시간초과 발생
 */
fun main() = with(System.`in`.bufferedReader()) {
    val roundCnt = readLine().toInt()
    val round = List(roundCnt) {
        readLine().split(" ").map { it.toInt() }
    }

    fun solve(selected:IntArray): Int {
        var order = 0
        var score = 0
        for(i in round.indices) {
            val field = IntArray(3) { 0 }

            var outCount = 0
            while (outCount != 3) {
                val now = selected[order]
                when(round[i][now]) {
                    0 -> {
                        outCount++
                    }
                    1 -> {
                        score += field[2]
                        field[2] = field[1]
                        field[1] = field[0]
                        field[0] = 1
                    }
                    2 -> {
                        score += field[2] + field[1]
                        field[2] = field[0]
                        field[1] = 1
                        field[0] = 0
                    }
                    3 -> {
                        score += field.sum()
                        field[2] = 1
                        field[1] = 0
                        field[0] = 0
                    }
                    4 -> {
                        score += field.sum() + 1
                        field[2] = 0
                        field[1] = 0
                        field[0] = 0
                    }
                }
                order = (order + 1) % 9
            }
        }
        return score
    }

    var max = 0
    fun perm(size:Int) {
        val visited = BooleanArray(size) { false }
        val selected = IntArray(size) { 0 }

        selected[3] = 0
        visited[0] = true

        fun perm(depth:Int) {
            if (depth == 3) {
                perm(depth + 1)
                return
            }

            if (depth == selected.size) {
                max = max.coerceAtLeast(solve(selected))
                return
            }

            for (i in selected.indices) {
                if (visited[i])
                    continue

                selected[depth] = i
                visited[i] = true
                perm(depth + 1)
                visited[i] = false
            }
        }

        perm(0)
    }
    perm(9)

    println(max)
}