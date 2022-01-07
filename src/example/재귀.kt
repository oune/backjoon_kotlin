package test.example

fun main() {
    foo(0, 3)
}

val arr = Array(101) { 0 }

fun foo(depth: Int, n: Int) {
    if (depth == n) {
        repeat(n) {
            print("${arr[it]} ")
        }
        println()
    }
    else {
        repeat(n) {
            arr[depth] = it
            foo(depth + 1, n)
        }
    }

}