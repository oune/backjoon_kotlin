package test.b3000to9999

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val tryCnt = readLine().toInt()
    repeat(tryCnt) {
        val input = readLine()
        val sb = StringBuilder()

        var protocol:String? = null
        var host:String? = null
        var port:String? = null
        var path:String? = null

        var hasPort = false
        var hasPath = false

        var idx = 0
        while (idx < input.length) {
            val char = input[idx]

            if (char == ':') {
                if (protocol == null) {
                    protocol = sb.toString()
                    sb.clear()
                    idx += 2
                } else {
                    host = sb.toString()
                    sb.clear()
                    hasPort = true
                }
            } else if (!hasPath && char == '/') {
                if (hasPort) {
                    port = sb.toString()
                    sb.clear()
                } else {
                    host = sb.toString()
                    sb.clear()
                }
                hasPath = true
            } else
                sb.append(char)

            idx++
        }

        if (sb.isNotEmpty()) {
            if (host == null) {
                host = sb.toString()
            } else if (hasPath) {
                path = sb.toString()
            } else if (hasPath) {
                port = sb.toString()
            }
        }

        protocol = protocol ?: "<default>"
        host = host ?: "<default>"
        port = port ?: "<default>"
        path = path ?: "<default>"

        val line = it + 1
        bw.appendLine("URL #$line")
        bw.appendLine("Protocol = $protocol")
        bw.appendLine("Host     = $host")
        bw.appendLine("Port     = $port")
        bw.appendLine("Path     = $path")
        bw.newLine()
    }
    bw.flush()
    bw.close()
}