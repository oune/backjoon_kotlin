package test.b5000

import java.io.BufferedReader

fun main() {
    val input = System.`in`.bufferedReader()
    val out = System.out.bufferedWriter()

    val repeat = input.readLine().toInt()
    repeat(repeat) {
        out.appendLine(if (check(input)) "NO" else "YES")
    }

    out.flush()
    out.close()
}

private class State(val isTerminal:Boolean) {
    val children:Array<State?> = Array<State?>(10){null}
}

fun check (input:BufferedReader) :Boolean {
    val phoneCount = input.readLine().toInt()
    val head = State(false)

    Array(phoneCount) {
        input.readLine()
    }.sorted().forEach { phone ->
        var pointer = head
        phone.forEachIndexed { idx, char ->
            val num = char.digitToInt()

            if (pointer.children[num] == null) {
                val newNode = State(idx == phone.lastIndex)
                pointer.children[num] = newNode
                pointer = newNode
            } else {
                pointer = pointer.children[num]!!
                if (pointer.isTerminal) {
                    return true
                }
            }
        }
    }
    return false
}

