package test
//
//fun main() {
//    val arr = arrayOf(9, 5, 1, 7, 3, 4, 6, 8, 9, 2)
//
//    merge(arr, 0, 9)
//
//    arr.forEach {
//        print("$it ")
//    }
//}
//
//fun merge(arr: Array<Int>, left:Int, right:Int) {
//    if (left == right){
//        return
//    }
//    val mid = (left + right) / 2
//    merge(arr, left, mid)
//    merge(arr, mid + 1, right)
//
//    var lIdx = left
//    var rIdx = mid + 1
//    val list = mutableListOf<Int>()
//
//    while(lIdx <= mid && rIdx <= right) {
//        if (arr[lIdx] <= arr[rIdx]){
//            list.add(arr[lIdx++])
//        } else {
//            list.add(arr[rIdx++])
//        }
//    }
//
//    while (lIdx <= mid)
//        list.add(arr[lIdx++])
//    while (rIdx <= right)
//        list.add(arr[rIdx++])
//
//    (left .. right).forEachIndexed { index, i ->
//        arr[i] = list[index]
//    }
//}