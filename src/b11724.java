import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B11724 {
    static boolean[] visited;
    static LinkedList[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n, m;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        Arrays.fill(visited, false);

        map = new LinkedList[n + 1];
        for (int i = 1; i < map.length; i++) {
            map[i] = new LinkedList<>();
        }

        int u, v;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            map[u].add(v);
            map[v].add(u);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                bfs(i);
                count++;
            }
        }

        bw.write(""+count);
        bw.flush();
        bw.close();
    }

    public static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        while(!que.isEmpty()) {
            int now = que.poll();
            visited[now] = true;
            LinkedList<Integer> grape = map[now];

            for (int point : grape) {
                if (!visited[point]) {
                    visited[point] = true;
                    que.add(point);
                }
            }
        }
    }
}


