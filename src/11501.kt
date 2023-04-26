/* 그리디
* 뒤에서 부터 탐색을 시작하여 최대 가격과 가격을 비교하여 최대 이익을 계산*/
fun main() {
    val testCase = readln().toInt()

    val res = List(testCase) {
        readln()
        val prices = readln().split(" ").map { it.toInt() }.asReversed()

        var money = 0L
        var max = 0
        for (price in prices) {
            if (price > max)
                max = price
            else
                money += max - price
        }
        money
    }

    print(res.joinToString("\n"))
}