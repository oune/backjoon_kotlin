import kotlin.system.exitProcess

/*
완전탐색, 백트랙킹

0으로 채워진 부분들 하나하나를 1~9 의 숫자로 채워 보면서 가능한 경우라면 탐색하고 아니면 백트랙킹 하면서 진행
내 코드에서는 먼저 0인 부분을 골라내고 넘어가면서 진행 했는데
스도쿠의 idx를 조정하는 과정이 조금 어색해서 오래걸렸음.
또한 배열을 일차원 배열로 펴서 작업을 진행하느랴고 조금 더 걸리는 문제가 있었는데
개발 편의성이 좋지 않아서 안하는게 좋지 않았나 싶음.
한번 시도를 해봤는데 백트랙킹으로 하는거가 문제, 백트랙킹으로 구현할때 재귀적으로 확인하고 진행하는
메인스택을 이용한 방법이 조금 더 편한듯

가능한지 여부를 확인하는 코드는 0이 아닌 숫자가 중복해서 나오면 불가능하다고 여김.
9번 기록을 하기 때문에 중복해서 나오지 않는다면 무조건 전부 한번씩 선택한 경우랑 동일함.

 */

fun main() = with(System.`in`.bufferedReader()) {
    val map = List(9) {
        readLine().toList().map { it.digitToInt()}
    }.flatten().toIntArray()

    val selectedIdx = map.withIndex().filter { it.value == 0 }.map { it.index }

    fun isPossible(idx:Int):Boolean {
        val rows = BooleanArray(10) { false }
        for (i in 0..8) {
            val r = (idx / 9) * 9 + i

            if (map[r] == 0)
                continue
            if (rows[map[r]])
                return false

            rows[map[r]] = true
        }

        val columns = BooleanArray(10) { false }
        for (i in 0..8) {
            val c = idx % 9 + i * 9

            if (map[c] == 0)
                continue

            if (columns[map[c]])
                return false

            columns[map[c]] = true
        }

        val box = BooleanArray(10) { false }
        val row = (idx / 27) * 3
        val column = (idx % 9 / 3) * 3
        for (i in 0..2) {
            for (j in 0..2) {
                val rowIdx = row + i
                val columnIdx = column + j
                val idx = rowIdx * 9 + columnIdx

                if (map[idx] == 0)
                    continue

                if (box[map[idx]])
                    return false

                box[map[idx]] = true
            }
        }

        return true
    }

    fun perm(depth:Int) {
        if (depth == selectedIdx.size) {
            println(map.toList().chunked(9).joinToString("\n"){ it.joinToString("") })
            exitProcess(0)
        }

        for (num in 1 .. 9) {
            val idx = selectedIdx[depth]
            map[idx] = num

            if (isPossible(idx))
                perm(depth + 1)

            map[idx] = 0
        }
    }
    perm(0)
}