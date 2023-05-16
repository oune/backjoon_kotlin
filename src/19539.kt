fun main() {
    readln()
    val numbs = readln().split(" ").map {it.toInt() }

    val one = numbs.sumOf { it % 2 }
    val two = numbs.sumOf { it / 2 }

    println(if (two >= one && (two - one) % 3 == 0) "YES" else "NO")
}