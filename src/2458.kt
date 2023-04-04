/*
플로이드 워샬
순서를 알수 있는 경우는 한 정점이 다른 정점들에 대해 갈수 있거나 올수 있는 경우
또한 다른 노드를 거쳐서 올수 있는지도 알아야 하기 때문에
플로이드 워셜 알고리즘을 이용하여 갈수 있는지에 대한 여부를 확인하고,
조건에 해당 된다면 count

틀렸습니다. 2%
가능한 여부 확인할때 비트마스킹(int)를 이용했는데 이때 500 이 들어가게 되면 overflow 가 발생
개수를 확인하지 않고 진행했던것이 문제

 */
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val arr = List(n) {
        BooleanArray(n) { false }
    }

    repeat(m) {
        val (from, to) = readln().split(" ").map { it.toInt() - 1}
        arr[from][to] = true
    }

    for (via in arr.indices) {
        for (from in arr.indices) {
            if (via == from)
                continue

            for (to in arr[from].indices) {
                if (via == to)
                    continue
                if (from == to)
                    continue

                arr[from][to] = (arr[from][via] && arr[via][to]) || arr[from][to]
            }
        }
    }

    var count = 0
    for (i in arr.indices) {
        val visited = BooleanArray(n) { false }
        visited[i] = true

        for (j in arr[i].indices) {
            if (arr[i][j])
                visited[j] = true
            if (arr[j][i])
                visited[j] = true
        }

        if (visited.all { it })
            count++
    }
    println(count)
}