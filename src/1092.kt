/*
* 그리디
* 무게제한이 있는 크레인이 한번에 한 박스를 옮길때
* 최소 시간을 구하라
*
* ❌(1%)
* 반례
3
1 5 10
8
1 2 4 5 9 9 10 10
>> 4
* */
fun main() {
    readln()
    val cranes = readln().split(" ").map { it.toInt() }.sortedDescending().toMutableList()
    readln()
    var boxes = readln().split(" ").map { it.toInt() }.sortedDescending().toMutableList()

    var count = 0
    while(boxes.isNotEmpty()) {
        for (crane in cranes) {
            for (box in boxes) {
                if (box <= crane) {
                    boxes.remove(box)
                    break
                }
            }
        }

        if (boxes.isNotEmpty() && cranes.all { it < boxes.first() }) {
            count = -1
            break
        }
        count++
    }

    println(count)
}