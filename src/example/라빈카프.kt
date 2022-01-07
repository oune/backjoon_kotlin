package test.example

private const val hm = 21956
private const val hk = 17
private var hk4 = 1

fun main() {
    val str = "HelloWorld"

    var sum = 0
    for (i in 0..3) {
        sum *= hk
        sum += str[i].code
        sum %= hm
        hk4 *= hk
        hk4 %= hm
    }
    println(sum)

    for (i in 4..9) {
        sum *= hk
        sum -= (str[i-4].code * hk4) % hm
        sum += str[i].code
        sum += hm
        sum %= hm
        println(sum)
    }
}