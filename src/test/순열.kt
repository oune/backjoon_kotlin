package test

private val data = Array(5){it + 1}
private val ans = Array(5){0}
private val visit = Array(5){false}

fun main() {
    perm(0, 5)
    println()
    perm(0, 5, 3)
    println()
    subSet(0, 5)
    println()
    subSet(0, 5, 0)
}

fun perm(depth:Int, n:Int) {
    if (depth == n) {
        for (i in 0 until n) {
            print("${ans[i]} ")
        }
        println()
        return
    }

    for (i in 0 until n) {
        if (!visit[i]) {
            ans[depth] = data[i]
            visit[i] = true
            perm(depth + 1, n)
            visit[i] = false
        }
    }
}

fun perm(depth:Int, n:Int, r:Int) {
    if (depth == r) {
        for (i in 0 until r) {
            print("${ans[i]} ")
        }
        println()
        return
    }

    for (i in 0 until n) {
        if (!visit[i]) {
            ans[depth] = data[i]
            visit[i] = true
            perm(depth + 1, n, r)
            visit[i] = false
        }
    }
}

fun subSet(depth:Int, n:Int) {
    if (depth == n) {
        for (i in 0 until n) {
            if (visit[i]) {
                print("${data[i]} ")
            }
        }
        println()
        return
    }

    visit[depth] = false
    subSet(depth + 1, n)
    visit[depth] = true
    subSet(depth + 1, n)
}

fun subSet(depth:Int, n:Int, cnt:Int) {
    if (depth == n) {
        if (cnt == 3) {
            for (i in 0 until n) {
                if (visit[i]) {
                    print("${data[i]} ")
                }
            }
            println()
        }
        return
    }

    visit[depth] = false
    subSet(depth + 1, n, cnt)
    visit[depth] = true
    subSet(depth + 1, n, cnt + 1)
}