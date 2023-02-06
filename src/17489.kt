fun main() = with(System.`in`.bufferedReader()) {
    val count = readLine().toInt()

    fun padding(sb:StringBuilder ,count:Int) {
        repeat(count) {
            sb.append("____")
        }
    }

    tailrec fun rec(count:Int, limit:Int, pre:StringBuilder, post:StringBuilder):String {
        padding(pre, count)
        pre.appendLine("\"재귀함수가 뭔가요?\"")

        if (count == limit) {
            padding(pre, count)
            pre.appendLine("\"재귀함수는 자기 자신을 호출하는 함수라네\"")
            post.insert(0, "라고 답변하였지.\n")
            repeat(count) {
                post.insert(0, "____")
            }
            return pre.append(post.toString()).toString()
        } else {
            padding(pre, count)
            pre.appendLine("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.")
            padding(pre, count)
            pre.appendLine("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.")
            padding(pre, count)
            pre.appendLine("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"")
        }

        post.insert(0, "라고 답변하였지.\n")
        repeat(count) {
            post.insert(0, "____")
        }

        return rec(count + 1, limit, pre, post);
    }

    fun res(count:Int):String {
        val pre = StringBuilder("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n")
        val post = StringBuilder()
        return rec(0, count, pre, post)
    }
    println(res(count))
}