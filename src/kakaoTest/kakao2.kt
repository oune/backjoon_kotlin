package test.kakaoTest

import kotlin.math.ceil

fun main() {
    val cal = { fees:IntArray, time:Int ->
        if (time > fees[0]) {
            fees[1] + ceil((time - fees[0]).toDouble()/ fees[2]) * fees[3]
        } else
            fees[1]
    }

    println(cal(intArrayOf(180,5000,10,600), 334))

    val arr = arrayOf(1, 2, 3, 4, 5, 3)
    arr.groupBy {
        it
    }

    println(arr.groupBy {
        it
    })


    val toMinute = { time:String ->
        val (hour, minute) = time.split(":").map { it.toInt() }
        hour * 60 + minute
    }

    println(toMinute("00:00"))
    println(toMinute("23:59"))
}