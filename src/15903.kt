import java.util.PriorityQueue

/*
* 그리디
* ❌(1%)
* int 타입으로 사용하면
* 1,000 * 15 번 연산, 최대 1,000,000
* 이므로 int 범위를 벗어난다.
* */
fun main() {
    val (_, m) = readln().split(" ").map { it.toInt() }

    val que = PriorityQueue<Long>()
    readln().split(" ").map { it.toLong() }.filter {
        que.add(it)
    }

    repeat(m) {
        val sum = que.poll() + que.poll()
        que.add(sum)
        que.add(sum)
    }

    println(que.sum())
}