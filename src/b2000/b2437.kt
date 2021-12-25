fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val weights = readLine().split(" ").map{ it.toInt() }.sorted()
//    println(weights)

    var goal = 1
    for (weight in weights) {
        if (weight <= goal)
            goal += weight
        else break
    }
    println(goal)
}