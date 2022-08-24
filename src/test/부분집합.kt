package test.test

fun main() {
    val arr = intArrayOf(9, 3, 6, 2)
    val selected = BooleanArray(arr.size) {
        false
    }

    listOf(false, true).forEach {
        selected[0] = it
        listOf(false, true).forEach {
            selected[1] = it
            listOf(false, true).forEach {
                selected[2] = it
                listOf(false, true).forEach {
                    selected[3] = it

                    arr.forEachIndexed { idx, it ->
                        if (selected[idx]) {
                            print("$it ")
                        }
                    }
                    println()
                }
            }
        }
    }

    println("===================")

    fun rec(depth:Int, end:Int) {
        if (depth == end) {
            arr.forEachIndexed { idx, it ->
                if (selected[idx]) {
                    print("$it ")
                }
            }
            println()
            return
        }

        listOf(false, true).forEach {
            selected[depth] = it

            rec(depth + 1, end)
        }
    }

    rec(0, 4)

    println("=================")
    val arr1 = intArrayOf(-1, 3, -9, 6, 7, -6, 1, 5, 4, -2)
    val selected1 = BooleanArray(arr1.size) { false }
    fun subset(depth:Int, end:Int) {
        if (depth == end) {
            var sum = 0
            arr1.forEachIndexed { idx, it ->
                if (selected1[idx]) {
                    sum += it
                }
            }

            if (sum == 0) {
                arr1.forEachIndexed { idx, it ->
                    if (selected1[idx]) {
                        print("$it ")
                    }
                }
                println()
            }

            return
        }

        listOf(false, true).forEach {
            selected1[depth] = it
            subset(depth + 1, end)
        }
    }

    subset(0, arr1.size)


    println("==================")

}