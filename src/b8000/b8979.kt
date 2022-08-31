package test.b8000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(' ').map { it.toInt() }

    data class Country(val name:Int, val gold:Int, val silver:Int, val bronze:Int)
    fun Country.isSameRank(other:Country) :Boolean {
        return gold == other.gold && silver == other.silver && bronze == other.bronze
    }

    val rank = Array(n) {
        val (name, gold, silver, bronze) = readLine().split(' ').map { it.toInt() }
        Country(name, gold, silver, bronze)
    }.sortedWith(
        compareBy<Country> { -it.gold }.thenBy { -it.silver }.thenBy { -it.bronze }
    )

    var pre = Country(0, -1, -1, -1)
    var preRank = 0

    for (idx in rank.indices) {
        val country = rank[idx]
        val countryRank = if (country.isSameRank(pre)) preRank else idx + 1

        if (country.name == k) {
            println(countryRank)
            break
        }

        pre = country
        preRank = countryRank
    }
}