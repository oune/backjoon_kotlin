fun main() {
    val humans = listOf("김싸피", "이싸피", "박싸피", "최싸피", "유유유", "호록로", "오오옹", "됴됴됴").sorted()

    fun List<String>.comb(r:Int): List<List<String>> {
        val combs = mutableListOf<List<String>>()
        for (masking in 0 until (1 shl this.size)) {
            val line = mutableListOf<String>()
            for (i in this.indices) {
                if (masking and (1 shl i) != 0)
                    line.add(this[i])
            }
            if (line.size == r)
                combs.add(line.sorted())
        }

        return combs
    }

    val combs = humans.comb(2)
    val visitedList = listOf(listOf("유유유", "이싸피"), listOf("유유유", "최싸피"))
    val filtered = combs.filter {
        it !in visitedList
    }

    println(filtered.joinToString("\n") { it. joinToString(", ")})
    println()

    fun List<List<String>>.getPairs(count:Int): MutableList<List<String>> {
        val choices = mutableListOf<List<String>>()
        for (masking in 0 until (1 shl this.size)) {
            val line = mutableListOf<String>()
            val selectCount = IntArray(humans.size) { 0 }

            for (i in this.indices) {
                if (masking and (1 shl i) != 0) {
                    val (a, b) = this[i]
                    line.add(this[i].joinToString(" "))
                    selectCount[humans.indexOf(a)]++
                    selectCount[humans.indexOf(b)]++
                }
            }

            if (line.size == count) {
                if (selectCount.all { it == 1 }) {
                    choices.add(line)
                }
            }
        }

        return choices
    }

    val choices = filtered.getPairs(humans.size / 2)
    println("경우의 수")
    println(choices.joinToString("\n"))

    val r = Math.random()
    val idx = (r * choices.size).toInt()

    println("최종 ${choices[idx]}")
}