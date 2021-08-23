fun main(args: Array<String>) {
    val (A,B) = readLine()!!.split(" ").map{ it.toInt() }
    println((A..B).count { num ->
        var n = num
        var c = 0
        for(p in 2..500) {
            while(n % p == 0) {
                n /= p
                c++
            }
        }
        if (n > 1) c++
        c != 1 && (2 until c).all{ c % it != 0 }
    })
}