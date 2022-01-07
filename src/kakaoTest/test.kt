package test.kakaoTest

fun main() {
    val check = { a: Int, b: Int ->
        a in 0..3 && 0 <= b && b < 4
    }

    for (i in -1..5) {
        for (j in -1 ..5) {
            println("$i $j : ${check(i, j)}")
        }
    }
}