import jdk.nashorn.internal.objects.NativeArray.pop
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val repeatCount = readLine()!!.toInt()

    val heap = Heap()
    val push = { num: Int ->
        heap.push(num)
    }
    val pop = {
        println(heap.pop())
    }

    repeat(repeatCount) {
        val input = readLine()!!.toInt()
        if (input == 0)
            pop()
        else
            push(input)
    }

}

class Heap() {
    private val arr = Array(300000) { -1 }
    private var top = 1

    fun print() {
        arr.filter {
            it != -1
        }.forEach {
            print("$it ")
        }
        println()
    }

    private fun isInIndex(index : Int): Boolean {
        return index in 1..300000
    }

    private fun swap(indexFrom: Int, indexTo: Int) {
        val temp = arr[indexFrom]
        arr[indexFrom] = arr[indexTo]
        arr[indexTo] = temp
    }

    private fun getParent(num: Int): Pair<Int, Int> {
        if (num == 1)
            return Pair(-1, 0)

        return Pair(num / 2, arr[num / 2])
    }

    fun push(num: Int) {
        var index = top
        arr[top++] = num

        var parent = getParent(index)

        while (parent.first > 0) {
            if (parent.second > num) {
                swap(parent.first, index)
                index = parent.first
            } else {
                break
            }

            parent = getParent(index)
        }
    }

    private fun getLeftChild(num: Int): Pair<Int, Int> {
        return if (isInIndex(num * 2)) {
            Pair(num * 2, arr[num * 2])
        } else {
            Pair(num * 2, -1)
        }
    }

    private fun getRightChild(num: Int): Pair<Int, Int> {
        return if (isInIndex(num * 2 + 1)) {
            Pair(num * 2 + 1, arr[num * 2 + 1])
        } else {
            Pair(num * 2 + 1, -1)
        }
    }

    fun pop(): Int {
        if (top == 1)
            return 0

        val res = arr[1]
        swap(1, --top)
        arr[top] = -1

        var index = 1
        while (index * 2 < top + 1) {
            val leftChild = getLeftChild(index)
            val rightChild = getRightChild(index)
            var min = Pair(-1, -1)

            if (leftChild.second == -1) {
                break
            } else if (rightChild.second == -1) {
                min = leftChild
            } else if (leftChild.second < rightChild.second) {
                min = leftChild
            } else if (leftChild.second >= rightChild.second) {
                min = rightChild
            }

            if (min.second < arr[index]) {
                swap(min.first, index)
                index = min.first
            } else
                break
        }

        return res
    }
}