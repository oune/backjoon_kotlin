import java.util.PriorityQueue

/*
* 그리디
* 작은 것부터 2개씩 합치기
*
* ❌(4%)
* 최소 한번의 정렬을 하게 된다면, 작은 2개가 합쳐진다는 것이 보장 되지 않음
* 그렇기에 우선순위 큐를 이용하여 가장 작은 2개가 합쳐진다는 것을 보장해야함.
* */
fun main() {
    val n = readln().toInt()

    val que = PriorityQueue<Int>()
    repeat(n) {
        que.add(readln().toInt())
    }

    var total = 0
    while(que.size > 1) {
        val sum = que.poll() + que.poll()
        total += sum
        que.add(sum)
    }
    println(total)
}