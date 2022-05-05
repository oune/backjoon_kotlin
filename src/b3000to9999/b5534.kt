package test.b3000to9999

fun main() = with(System.`in`.bufferedReader()) {
    val lineCnt = readLine().toInt();
    val newName = readLine();
    var cnt = 0

    repeat(lineCnt) {
        var charCnt: Int
        val name = readLine();

        loop@ for (i in name.indices) {
            if (name[i] == newName[0]) {
                for (j in i + 1 until name.length) {
                    if (name[j] == newName[1]){
                        charCnt = 2

                        val distance = j - i;
                        var idx = j + distance;
                        while (idx < name.length && charCnt < newName.length) {
                            if (name[idx] != newName[charCnt])
                                break;

                            charCnt++;
                            idx += distance;
                        }

                        if (charCnt == newName.length) {
                            cnt++
                            break@loop
                        }
                    }
                }
            }
        }
    }

    print(cnt);
}