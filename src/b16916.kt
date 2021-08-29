fun main() = with(System.`in`.bufferedReader()) {
    val p = readLine()
    val s = readLine()

    val hm = listOf(71956, 239333, 20873, 2163201)
    val hk = listOf(37, 31, 23, 3311)
    val hkn = mutableListOf(1, 1, 1, 1)
    val ans = mutableListOf(false, false, false, false)

    repeat(hm.size) {
        var target = 0
        for (char in s) {
            target *= hk[it]
            target += char.code
            target %= hm[it]

            hkn[it] *= hk[it]
            hkn[it] %= hm[it]
        }

        var sum = 0
        for (i in s.indices) {
            sum *= hk[it]
            sum += p[i].code
            sum %= hm[it]
        }
        ans[it] = sum == target
        for (i in s.length until p.length) {
            println(it)
            sum *= hk[it]
            sum -= (p[i - s.length].code * hkn[it]) % hm[it]
            sum += p[i].code
            sum += hm[it]
            sum %= hm[it]

            if (sum == target) {
                ans[it] = true
                break
            }
        }
    }

    print(if (ans.all { it }) "1" else "0")
}