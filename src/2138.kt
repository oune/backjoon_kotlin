/*
* 그리디
* 전구를 켜면 -1, 0, 1 위치의 전구의 상태가 변함
* 목표물에 맞는 전구로 변화를 시키고 나서, 다시 바꾸지 않으면 최적임
* 그렇기에 목표와 다르다면 전구를 변화시키고 다시는 해당 위치를 변경하지 않으면 됨.
* 다만 처음스위치를 눌른 경우와 누르지 않은 경우를 분리 하지 않으면 그리디하게 확일이 불가능함
* 따라서 스위치를 눌른 경우의 횟수와 누르지 않은 경우의 횟수를 확인하여 계산
*
* ❌(4%)
반례
2
111
000
* Int.maxvalue 에 +1 을 하는 경우가 발생하여 오버플로우 가능성이 존재
* */
fun main() {
    readln()
    val from = readln()
    val to = readln().toList()
    val inf = 200001

    fun CharArray.toggle(idx:Int) {
        this[idx] = when(this[idx]) {
            '0' -> '1'
            '1' -> '0'
            else -> 'X'
        }
    }

    fun CharArray.count(to:List<Char>):Int {
        var count = 0
        for (i in 0 until this.lastIndex) {
            if (this[i] != to[i]) {
                (0..2).map {
                    i + it
                }.filter {
                    it in this.indices
                }.forEach {
                    this.toggle(it)
                }

                count++
            }
        }

        if (this.toList() == to)
            return count
        return inf
    }

    val one = from.toCharArray().count(to)

    val clone = from.toCharArray()
    for (i in 0..1)
        clone.toggle(i)
    val two = clone.count(to) + 1

    val min = minOf(one, two)
    val res = if (min >= inf) -1 else min
    println(res)
}