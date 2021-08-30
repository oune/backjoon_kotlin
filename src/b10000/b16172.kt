package b10000

private val hm = listOf(21956, 21487, 20873, 21601)
private val hk = listOf(17, 19, 23, 31)
private var hkn = mutableListOf(1, 1, 1, 1)
private var ans = mutableListOf(false, false, false, false)

fun main() = with(System.`in`.bufferedReader()) {
    val book = readLine().filter { it < '0' || it > '9' }
    val text = readLine()

    repeat(hm.size) {
        var hashedText = 0
        for (char in text) {
            hashedText *= hk[it]
            hashedText += char.code
            hashedText %= hm[it]
            hkn[it] *= hk[it]
            hkn[it] %= hm[it]
        }

        var sum = 0
        for (i in text.indices) {
            sum *= hk[it]
            sum += book[i].code
            sum %= hm[it]
        }
        ans[it] = sum == hashedText
        for (i in text.length until book.length) {
            sum *= hk[it]
            sum -= (book[i - text.length].code * hkn[it]) % hm[it]
            sum += book[i].code
            sum += hm[it]
            sum %= hm[it]

            if (sum == hashedText) {
                ans[it] = true
                break
            }
        }
    }
    print(if(ans.all { it }) "1" else "0")
}