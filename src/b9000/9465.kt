package b9000fun main() = with(System.`in`.bufferedReader()) {

    repeat(readLine().toInt()) {
        val size = readLine().toInt()
        val arr = Array(2) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        for (j in 0 until size) {
            for (i in arr.indices) {
                val now = arr[i][j]
                val pre1 = if (j - 1 in arr[i].indices) arr[if (i == 0) 1 else 0][j - 1] else 0
                val pre2 = if (j - 2 in arr[i].indices) arr[if (i == 0) 1 else 0][j - 2] else 0

                arr[i][j] = now + pre1.coerceAtLeast(pre2)
            }
        }

        println(arr.maxOf { it.max() })
    }
}