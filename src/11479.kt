import kotlin.math.abs

/*
* 그리디
* 차이가 가장 적은건 올라갔다 내려가는 구조이므로
* 정렬은 먼저 수행하고 짝수번의 원소들을 나열, 홀수번의 원소들을 나열하면
* 차이가 가장 적은 리스트를 만들수 있다. 이후 해당 리스트에서의 차이의 최대를 확인하면 된다.
* */
fun main() {
    val testcaseCnt = readln().toInt()
    val res = List(testcaseCnt) {
        readln()
        val numbs = readln().split(" ").map { it.toInt() }
        val sorted = numbs.sorted()

        val front = sorted.filterIndexed { index, _ ->
            index % 2 == 0
        }
        val back = sorted.filterIndexed { index, _ ->
            index % 2 == 1
        }

        val list = front + back.asReversed()
        var pre = list.first()
        var distance = 0
        for (num in list.drop(1)) {
            distance = distance.coerceAtLeast(abs(pre - num))
            pre = num
        }
        distance
    }

    print(res.joinToString("\n"))
}
