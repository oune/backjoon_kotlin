package test.b2000

fun main() = with(System.`in`.bufferedReader()) {
    fun check(input:String) :Int{
        data class Node(val bracket:Char, val num:Int, val isNumber:Boolean)
        val stack = mutableListOf<Node>()
        input.forEach {
            when(it) {
                '(' -> {
                    stack.add(Node('(', 2, false))
                } ')' -> {
                    var sum = 0
                    do {
                        if (stack.isEmpty())
                            return 0

                        val now = stack.removeLast()

                        if (now.isNumber) {
                            sum += now.num
                        } else if (now.bracket == '('){
                            if(sum == 0)
                                sum = 1
                            sum *= 2
                        } else {
                            return 0
                        }
                    } while(now.isNumber)

                    stack.add(Node('n', sum, true))
                } ']' -> {
                    var sum = 0
                    do {
                        if (stack.isEmpty())
                            return 0

                        val now = stack.removeLast()

                        if (now.isNumber) {
                            sum += now.num
                        } else if (now.bracket == '['){
                            if(sum == 0)
                                sum = 1
                            sum *= 3
                        } else {
                            return 0
                        }
                    } while(now.isNumber)

                    stack.add(Node('n', sum, true))
                } '[' -> {
                    stack.add(Node('[', 3, false))
                }
            }
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