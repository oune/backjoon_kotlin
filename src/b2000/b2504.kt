package test.b2000

fun main() = with(System.`in`.bufferedReader()) {
    fun check(input:String) :Int{
        data class Node(val bracket:Char, val num:Int, val isNumber:Boolean)
        val stack = mutableListOf<Node>()

        fun sum(stack:MutableList<Node>, target:Char, multiple:Int) :Int {
            var sum = 0
            do {
                if (stack.isEmpty())
                    return 0

                val now = stack.removeLast()

                if (now.isNumber) {
                    sum += now.num
                } else if (now.bracket == target){
                    if(sum == 0)
                        sum = 1
                    sum *= multiple
                } else {
                    return 0
                }
            } while(now.isNumber)
            return sum
        }

        input.forEach {
            val node = when(it) {
                '(' -> {
                    Node('(', 2, false)
                } ')' -> {
                    Node('n', sum(stack, '(', 2), true)
                } ']' -> {
                    Node('n', sum(stack, '[', 3), true)
                } '[' -> {
                    Node('[', 3, false)
                } else -> {
                    Node('x', 0, false)
                }
            }
            if (node.num == 0)
                return 0

            stack.add(node)
        }

        var sum = 0
        while(stack.isNotEmpty()) {
            val node = stack.removeLast()
            if (node.isNumber)
                sum += node.num
            else {
                return 0
            }
        }
        return sum
    }

    println(check(readLine()))
}