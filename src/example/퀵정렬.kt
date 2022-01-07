package test.example

fun main() {
    val arr = arrayOf(9, 5, 1, 7, 3, 4, 6, 8, 9, 2)

    quickSort(arr, 0, 9)

    arr.forEach {
        print("$it ")
    }
}

fun quickSort(arr: Array<Int>, left: Int, right: Int) {
    var leftPoint = left
    var rightPoint = right
    val pivot = arr[(left + right) / 2]

    while( leftPoint <= rightPoint ) {
        while (arr[leftPoint] < pivot)
            leftPoint++
        while (arr[rightPoint] > pivot)
            rightPoint--

        if (leftPoint <= rightPoint) {
            val temp = arr[leftPoint]
            arr[leftPoint] = arr[rightPoint]
            arr[rightPoint] = temp
            leftPoint++
            rightPoint--
        }
    }
    if (leftPoint < right)
        quickSort(arr, left, rightPoint)
    if (rightPoint > left)
        quickSort(arr, leftPoint, right)
}