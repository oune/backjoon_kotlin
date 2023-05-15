/*
* 그리디
* n = 10^6 최소 O(n) 으로 해결해야함.
* 화살은 곧게 나아가고 풍선을 만나면 한칸 내려감
* 화살의 위치를 가지는 배열을 준비하고, 풍선의 위치에 해당하는 화살이 없으면 카운트
* 풍선이 있으면 화살의 위치를 변경
* 화살의 배열은 여러개 있을수 있으므로 int
* */
fun main() {
    readln()
    val balloons = readln().split(" ").map { it.toInt() }
    val arrows = IntArray(1000001) { 0 }

    var count = 0
    for (balloon in balloons) {
        if (arrows[balloon] == 0) {
            count++
        } else {
            arrows[balloon]--
        }
        arrows[balloon - 1]++
    }

    println(count)
}