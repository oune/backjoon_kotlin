package test.example
//
//fun b1000.b3000to9999.b10000.main() {
//    val `1000~1999`.b1000.arr = arrayOf(9, 5, 1, 7, 3, 4, 6, 8, 9, 2)
//
//    merge(`1000~1999`.b1000.arr, 0, 9)
//
//    `1000~1999`.b1000.arr.forEach {
//        print("$it ")
//    }
//}
//
//fun merge(`1000~1999`.b1000.arr: Array<Int>, left:Int, right:Int) {
//    if (left == right){
//        return
//    }
//    val mid = (left + right) / 2
//    merge(`1000~1999`.b1000.arr, left, mid)
//    merge(`1000~1999`.b1000.arr, mid + 1, right)
//
//    var lIdx = left
//    var rIdx = mid + 1
//    val list = mutableListOf<Int>()
//
//    while(lIdx <= mid && rIdx <= right) {
//        if (`1000~1999`.b1000.arr[lIdx] <= `1000~1999`.b1000.arr[rIdx]){
//            list.add(`1000~1999`.b1000.arr[lIdx++])
//        } else {
//            list.add(`1000~1999`.b1000.arr[rIdx++])
//        }
//    }
//
//    while (lIdx <= mid)
//        list.add(`1000~1999`.b1000.arr[lIdx++])
//    while (rIdx <= right)
//        list.add(`1000~1999`.b1000.arr[rIdx++])
//
//    (left .. right).forEachIndexed { index, i ->
//        `1000~1999`.b1000.arr[i] = list[index]
//    }
//}