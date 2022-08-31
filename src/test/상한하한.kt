package test.test

fun main() {
    fun upperBound(arr:IntArray, target:Int) :Int{
        var low = 0
        var high = arr.lastIndex

        while(low <= high) {
            val mid = (low + high) / 2
            if (arr[mid] > target)
                high = mid - 1
            else
                low = mid + 1
        }
        return low
    }

    fun lowerBound(arr:IntArray, target:Int) :Int{
        var low = 0
        var high = arr.lastIndex

        while(low <= high) {
            val mid = (low + high) / 2
            if (arr[mid] < target)
                low = mid + 1
            else
                high = mid - 1
        }
        return low
    }
}