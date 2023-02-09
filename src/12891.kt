fun main() = with(System.`in`.bufferedReader()) {
    val (_, p) = readLine().split(" ").map { it.toInt() }
    val dna = readLine().toList()
    val (a, c, g, t) = readLine().split(" ").map { it.toInt() }

    var count = 0
    val counts = IntArray(4)

    fun countUp(idx:Int) {
        when(dna[idx]) {
            'A' -> {
                counts[0]++
            }
            'C' -> {
                counts[1]++
            }
            'G' -> {
                counts[2]++
            }
            'T' -> {
                counts[3]++
            }
        }
    }

    fun countDown(idx:Int) {
        when(dna[idx]) {
            'A' -> {
                counts[0]--
            }
            'C' -> {
                counts[1]--
            }
            'G' -> {
                counts[2]--
            }
            'T' -> {
                counts[3]--
            }
        }
    }

    fun check() {
        if (counts[0] >= a && counts[1] >= c && counts[2] >= g && counts[3] >= t) {
            count++
        }
    }

    for (i in 0 until p) {
        countUp(i)
    }
    check()

    for (i in p  until dna.size) {
        countUp(i)
        countDown(i - p)
        check()
    }

    println(count)
}