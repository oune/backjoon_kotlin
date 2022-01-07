package test.kakaoTest

import kotlin.math.ceil

fun main() {
    val fees = intArrayOf(180, 5000, 10, 600)
    val records = arrayOf("05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT")
    val res = solution(fees, records)
    println(res.contentToString())
}

fun solution(fees: IntArray, records: Array<String>): IntArray {
    val list = records.map{
        val (first, second, third) = it.split(" ")
        Triple(first, second, third)
    }

    val cars = list.map{ it.second }.toSet().sorted()
    val idxMap = HashMap<String, Int>()
    cars.forEachIndexed { index, s ->
        idxMap[s] = index
    }
    var answer = IntArray(cars.size){0}

    val calFee = { time:Int ->
        if (time > fees[0]) {
            (fees[1] + ceil((time - fees[0]).toDouble()/ fees[2]) * fees[3]).toInt()
        } else
            fees[1]
    }

    val toMinute = { time:String ->
        val (hour, minute) = time.split(":").map { it.toInt() }
        hour * 60 + minute
    }
    println(answer.contentToString())
    val inTimes = Array(cars.size){ "" }
    list.sortedBy {
        it.first
    }.forEach {
        val (time, car, dir) = it
        if (dir == "IN") {
            inTimes[idxMap[car]!!] = time
        } else {
            answer[idxMap[car]!!] += toMinute(time) - toMinute(inTimes[idxMap[car]!!])
            inTimes[idxMap[car]!!] = ""
        }
    }
    println(answer.contentToString())
    inTimes.forEachIndexed { index, s ->
        if (s != "") {
            answer[index] += toMinute("23:59") - toMinute(s)
        }
    }

    println(answer.contentToString())
    val finalAnswer = IntArray(answer.size)
    for (i in answer.indices) {
        finalAnswer[i] = calFee(answer[i])
    }

    return finalAnswer
}