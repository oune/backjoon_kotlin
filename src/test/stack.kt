package test

class Stack {
    private var size = 10
    private val arr = Array<Int>(size){ 0 }
    private var top = 0

    fun push(num: Int) {
        arr[top++] = num
    }

    fun pop(): Int {
        return arr[--top]
    }

    fun isEmpty(): Boolean {
        return top == 0
    }

    fun size(): Int {
        return top
    }

    fun top(): Int {
        return arr[top - 1]
    }
}

fun main() {
    val stack = Stack()
    stack.push(5)
    stack.push(3)
    stack.push(9)
    println(stack.pop())
    println(stack.pop())
    stack.push(7)
    stack.push(2)
    stack.push(8)
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
}