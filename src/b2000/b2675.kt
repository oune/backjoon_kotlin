package test.b2000

fun main() {
    val case = readLine()!!.toInt()
    val out = System.out.bufferedWriter()

    repeat(case) {
        val (repeat, str) = readLine()!!.split(" ")
        val repeatNum = repeat.toInt()

        str.forEach { char ->
            repeat(repeatNum) {
                out.append(char)
            }
        }
        out.newLine()
    }
    out.flush()
}