import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()
    val size = num + 1
    val che = Array( size ) { true }
    che[0] = false
    che[1] = false
    for (i in 2..sqrt(size.toDouble()).toInt()) {
        var idx = i * i
        while (idx < size) {
            che[idx] = false
            idx += i
        }
    }

    val primes = (1..num).filter { che[it] } + 0
    var left = 0
    var right = 0
    var sum = 0
    var count = 0
    while(right < primes.size) {
        if (sum == num)
            count++
        if (sum > num) {
            sum -= primes[left++]
        } else {
            sum += primes[right++]
        }
    }

    print(count)
}