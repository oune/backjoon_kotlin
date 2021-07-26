import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    private int first;
    private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}

public class b11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n, m;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        Queue<Integer> que = new LinkedList<>();
        LinkedList<Pair> list = new LinkedList<>();

        int u, v;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            list.add(new Pair(u, v));
        }

        int count = 0;
        while(!list.isEmpty()) {
            count++;
            Pair side = list.get(0);
            list.remove(0);

            que.add(side.getFirst());
            que.add(side.getSecond());

            while (!que.isEmpty()) {
                int now = que.poll();
                visited[now] = true;

                for (int i = 0; i < list.size(); i++) {
                    Pair pair = list.get(i);
                    int visit = -1;

                    if (pair.getFirst() == now) {
                        visited[pair.getSecond()] = true;
                        visit = pair.getFirst();
                        list.remove(i);
                    } else if (pair.getSecond() == now) {
                        visited[pair.getFirst()] = true;
                        visit = pair.getSecond();
                        list.remove(i);
                    }
                    if (visit != -1 && !visited[visit]) {
                        que.add(visit);
                    }
                }
            }
        }

        bw.write(""+count);
        bw.flush();
        bw.close();
    }
}
