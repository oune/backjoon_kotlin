import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val count = readLine().toInt()
    val arr = Array(count) { readLine().toInt() }

    merge(arr, 0, count - 1)

    val out = BufferedWriter(OutputStreamWriter(System.out))
    arr.forEach {
        out.appendLine(it.toString())
    }
    out.flush()
}
fun merge(arr: Array<Int>, left:Int, right:Int) {
    if (left == right){
        return
    }
    val mid = (left + right) / 2
    merge(arr, left, mid)
    merge(arr, mid + 1, right)

    var lIdx = left
    var rIdx = mid + 1
    val list = mutableListOf<Int>()

    while(lIdx <= mid && rIdx <= right) {
        if (arr[lIdx] <= arr[rIdx]){
            list.add(arr[lIdx++])
        } else {
            list.add(arr[rIdx++])
        }
    }

    while (lIdx <= mid)
        list.add(arr[lIdx++])
    while (rIdx <= right)
        list.add(arr[rIdx++])

    (left .. right).forEachIndexed { index, i ->
        arr[i] = list[index]
    }
}