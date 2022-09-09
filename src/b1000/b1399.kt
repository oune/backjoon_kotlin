package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    val out =  System.out.bufferedWriter()

    repeat(readLine().toInt()) {
        val (k, m) = readLine().split(' ').map { it.toInt() }

        val arr = IntArray(10) { -1 }
        var pre = 0
        var goldNumber = 1
        val visited = BooleanArray(arr.size) { false }
        var count = 0
        var loop = 0

        while(true) {
            while(goldNumber > 9) {
                 goldNumber = goldNumber.toString().fold(0) {
                     acc, c -> acc + c.digitToInt()
                 }
            }
            arr[pre] = goldNumber

            if (visited[goldNumber]) {
                loop = goldNumber
                var gold = goldNumber
                do {
                    gold = arr[gold]
                    count++
                } while (gold != goldNumber)
            }

            if (arr[goldNumber] != -1)
                break

            visited[goldNumber] = true
            pre = goldNumber
            goldNumber *= m
        }

        var x = 0
        var y = 0
        goldNumber = 1

        var i = 0
        while (i < k) {
            if (goldNumber == loop) {
                i += (k - i) / (count * 4) * (count * 4)

                if (i == k)
                    break
            }

            when(i % 4) {
                0 -> { y += goldNumber }
                1 -> { x += goldNumber }
                2 -> { y -= goldNumber }
                3 -> { x -= goldNumber }
            }

            goldNumber = arr[goldNumber]
            i++
        }

        out.appendLine("$x $y")
    }
    out.flush()
}