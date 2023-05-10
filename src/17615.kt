/*
* 그리디
* 끝에 같은 색상의 공들이 있다면 그 공들은 다시 옮길 필요가 없음
* 따라서 연속된 같은 색상이 있는쪽으로 같은 색을 이동시키면 됨
* 또한 공을 이동하고 난 뒤의 도착지가 연속된 끝에 위치하게 되면 최선
* 생각해보니 그냥 개수만 세도 되겠는데? >> 불가능
*
* ❌(1%)
* 그냥 세면 안되네
* 10
* RBRRBBBBRR
* ans: 3
* */
fun main() {
    readln()
    val balls = readln().toList()

    fun List<Char>.countColor(color: Char):Int {
        var isCountStart = false
        var count = 0
        for (ball in this) {
            if (ball == color) {
                if (isCountStart)
                    count++
            } else {
                isCountStart = true
            }
        }
        return count
    }

    val a = balls.countColor('R')
    val b = balls.countColor('B')
    val c = balls.reversed().countColor('R')
    val d = balls.reversed().countColor('B')
//    println("$a $b $c $d")
    println(minOf(a, b, c, d))
}