/*
Lis
앞에서 시작하는 Lis의 마지막과
뒤에서 시작하는 Lis의 마지막이 같고
두 Lis 길이의 합 - 1 이 원래 수열의 길이와 같으면 바이토닉 수열


 */
fun main() {
    readln()
    val numbs = readln().split(" ").map { it.toInt() }

    fun List<Int>.lis(): IntArray {
        val lis = IntArray(this.size) { 0 }
        for (i in this.indices) {
            lis[i] = 1

            for (j in i downTo 0) {
                if (this[i] > this[j]) {
                    lis[i] = lis[i].coerceAtLeast(lis[j] + 1)
                }
            }
        }

        return lis
    }

    val lis = numbs.lis()
    val lds = numbs.reversed().lis().reversed()

    var max = 0
    for (i in lis.indices) {
        val sum = lis[i] + lds[i]
        max = max.coerceAtLeast(sum)
    }

    println(max - 1)
}