package b1000

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val sum = readLine().split(" ").sumOf { it.toInt() }
    val people = Stack<String>()
    val list = mutableListOf<String>()

    repeat(sum) {
        val person = readLine()
        list.add(person)
    }

    var pre = ""
    list.sorted().forEach {
        if (pre == it)
            people.push(it)
        pre = it
    }

    val out = BufferedWriter(OutputStreamWriter(System.out))
    out.appendLine(people.size.toString())
    people.forEach {
        out.appendLine(it)
    }
    out.flush()
    out.close()
}