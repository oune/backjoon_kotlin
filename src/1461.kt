import kotlin.math.abs

/*
* 그리디
* 책을 가져다두기 위해 가고 나면 다시 새로운 책을 챙기기 위해서 원점으로 돌아와야함
* 새로운 책이 없는 경우 돌아오지 않아도 됨
* 그렇기에 m개씩 책을 챙길때 큰거부터 m개씩 챙기고, 가장 거리가 먼 거리를 두고 나머지 거리를 왕복한 만큼 2배를 곱함.
* 큰거부터 m개씩 챙겨야 남는 물건이 생겼을때 작은 왕복거리가 생김
* */
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val bookPos = readln().split(" ").map { it.toInt() }.sorted()

    val negative = bookPos.filter { it < 0 }
    val positive = bookPos.filter { it > 0 }

    val chunkedNegative = negative.chunked(m)
    val chunkedPositive = positive.asReversed().chunked(m)

    val distances = chunkedNegative.map { list ->
        list.maxOf { abs(it) }
    } + chunkedPositive.map { it.max() }

    val sorted = distances.sortedDescending()
    val total = sorted.first() + sorted.drop(1).sum() * 2
    println(total)
}