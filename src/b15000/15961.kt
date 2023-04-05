import kotlin.math.max

/*
슬라이딩 윈도우
 */
fun main() {
    val (dishCnt, sushiTypeCnt, choice, coupon) = readln().split(" ").map { it.toInt() }

    val list = List(dishCnt) {
        readln().toInt()
    }
    val counts = IntArray(sushiTypeCnt + 1) { 0 }
    val set = HashSet<Int>()
    val sushi = list + list

    counts[coupon] = 1
    set.add(coupon)

    var max = 0
    for (i in 0 until choice) {
        val now = sushi[i]

        counts[now]++
        set.add(now)
        max = set.size
    }

    for (i in choice until dishCnt + choice) {
        val first = sushi[i - choice]
        counts[first]--

        if (counts[first] == 0)
            set.remove(first)

        val now = sushi[i]
        counts[now]++
        set.add(now)

        max = max(max, set.size)
    }

    println(max)
}