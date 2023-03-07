import java.util.LinkedList

/*
위상 정렬
팀 1번 부터 시작
상대적인 순위를 가지고 원래 순위를 찾아내기

작년 등수도 map 에 넣어서 시작 >> 순서가 바뀐경우 처리
1. 찾아낼수 없는 경우 확인, 2. 일관성이 없는 경우 확인, 3. 순위 출력

틀린이유는 from, to 대소가 존재한다고 생각했지만 존재하지 않았고, 결국 서로 바꾸는 것
그래프의 방향을 바꾸고 count 를 증감하는방식으로 변경
일관성이 없는 경우 : cycle
찾아 낼 수 없는 경우 : que에 2개가 들어가는 경우

1시간 이상 추정
 */
fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder();

    val testcaseCnt = readLine().toInt()
    repeat(testcaseCnt) {
        val teamCnt = readLine().toInt()
        val map = List(teamCnt + 1) {
            LinkedList<Int>()
        }
        val indegree = IntArray(map.size) { 0 }

        val teams = readLine().split(" ").map { it.toInt() }
        for (i in teams.indices) {
            for (j in i + 1 .. teams.lastIndex) {
                map[teams[i]].add(teams[j])
                indegree[teams[j]]++
            }
        }

        val changeCnt = readLine().toInt()
        repeat(changeCnt) {
            val (from, to) = readLine().split(" ").map { it.toInt() }

            if (from in map[to]) {
                map[to].remove(from)
                indegree[from]--
                map[from].add(to)
                indegree[to]++
            } else {
                map[from].remove(to)
                indegree[to]--
                map[to].add(from)
                indegree[from]++
            }
        }

        val ans = mutableListOf<Int>()
        val que = LinkedList<Int>()
        for (i in 1..indegree.lastIndex) {
            if (indegree[i] == 0)
                que.offer(i)
        }

        var isOnly = true
        while(que.isNotEmpty()) {
            if (que.size > 1)
                isOnly = false

            val now = que.poll()

            ans.add(now)

            for (next in map[now]) {
                indegree[next]--
                if (indegree[next] == 0)
                    que.offer(next)
            }
        }

        val isPossible = ans.size == teamCnt
        if (!isPossible) {
            sb.appendLine("IMPOSSIBLE")
        } else if (!isOnly) {
            sb.appendLine("?")
        } else {
            sb.appendLine(ans.joinToString(" "))
        }
    }
    print(sb)
}