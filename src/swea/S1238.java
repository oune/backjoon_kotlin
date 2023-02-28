import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    public int pos;
    public int depth;

    public Node(int pos, int depth) {
        this.pos = pos;
        this.depth = depth;
    }
}
public class S1238 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final int testcaseCnt = 10;
        for (int t = 0; t < testcaseCnt; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int length = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            LinkedList<Integer>[] map = new LinkedList[101];
            for (int i = 0; i < map.length; i++) {
                map[i] = new LinkedList<>();
            }

            st = new StringTokenizer(br.readLine(), " ");
            while(st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                map[from].add(to);
            }

            PriorityQueue<Node> que = new PriorityQueue<>(
                    Comparator.comparingInt((Node it) -> it.depth)
                            .thenComparingInt(it -> it.pos)
            );
            boolean[] visited = new boolean[101];
            Arrays.fill(visited, false);

            que.offer(new Node(start, 0));
            visited[start] = true;

            int last = start;
            while(!que.isEmpty()) {
                Node now = que.poll();
                last = now.pos;

                for (Integer next : map[now.pos]) {
                    if (visited[next])
                        continue;

                    que.offer(new Node(next, now.depth + 1));
                    visited[next] = true;
                }
            }

            sb.append("#");
            sb.append(t + 1);
            sb.append(" ");
            sb.append(last);
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
