fun main() {
    val input = readLine()

    val len = input!!.length
    (1 until len).forEach {
        (it + 1 until len).forEach {
            print("!")
        }
    }
}