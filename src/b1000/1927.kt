package test.b1000

fun main() = with(System.`in`.bufferedReader()) {
    val operatorCnt = readLine().toInt()

    val arr = IntArray(100001) { 0 }
    var idx = 1

    fun insert(num:Int) {
        var pre = idx
        while(pre != 1) {
            if (arr[pre/2] <= num)
                break

            arr[pre] = arr[pre/2]
            pre /= 2
        }
        arr[pre] = num
        idx++
    }

    fun delete() :Int{
        if (idx == 1)
            return 0

        val ans = arr[1]
        val num = arr[--idx]
        var i = 2

        while(i < idx) {
            if (i + 1 < idx)
                if (arr[i + 1] < arr[i])
                    i++

            if (arr[i] < num)
                arr[i / 2] = arr[i]
            else
                break
        }
        arr[i/2] = num
        return ans
    }

    val out = System.out.bufferedWriter()
    repeat(operatorCnt) {
        when(readLine().toInt()) {
            0 -> { out.appendLine(delete().toString())
            }
            else -> { insert(it)
            }
        }
    }
    out.flush()
}