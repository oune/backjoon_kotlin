/*
* 그리디
* k개의 집중국을 설치하고, 수신 거리의 합을 계산한다.
* 이때 k개의 집중국을 설치하는 경우 k - 1 개의 센서거리를 계산하지 않을 수 있다.
* 또한 수신 가능 거리는 차이들의 합으로 계산할 수 있다.
* 5, 6, 7 이 있을때
* 거리의 차는 1, 1 이고
* 1개의 집중국을 6 에 설치하면 왼쪽 1, 오른쪽 1 총 2의 길이를 가진다.
* 이는 가장작은 5에 설치하고, 7까지 닿는 수신가능 거리와 똑같으므로,
* 이전값과의 차이를 이용한 계산이 가능하다.
* 그렇기에 오름차순으로 정렬한뒤 차이를 계산하고, 그 차이가 큰 k - 1개를 제거한 것의 합이 정답이다.
* */
fun main() {
    readln()
    val k = readln().toInt()

    val highways = readln().split(" ").map { it.toInt() }.sorted()
    val diff = highways.drop(1).mapIndexed { index, i ->
        i - highways[index]
    }
    println(diff.sortedDescending().drop(k - 1).sum())
}