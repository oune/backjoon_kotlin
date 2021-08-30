fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine()
    val stack = ArrayList<Char>()
    var sum = 0
    var pre = '.'
    input.forEach {
        if (it == '(') {
            stack.add(it)
        } else {
            stack.removeLast()
            if (pre == '(') {
               sum += stack.size
            } else {
                sum++
            }
        }
        pre = it
    }
    print(sum)
}