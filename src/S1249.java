import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class S1249 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            int size = Integer.parseInt(br.readLine());
            int[][] map = new int[size][size];

            for (int i = 0; i < size; i++) {
                String line = br.readLine();
                for (int j = 0; j < size; j++) {
                    map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }

            Sol solution = new Sol(size, map);
            int min = solution.dijkstra(new Point(0, 0));
            sb.append("#");
            sb.append(tc);
            sb.append(" ");
            sb.append(min);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class State {
    Point point;
    int cost;

    public State(Point point, int cost) {
        this.point = point;
        this.cost = cost;
    }
}
class Sol {
    int size;
    int[][] map;

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public Sol(int size, int[][] map) {
        this.size = size;
        this.map = map;
    }

    public int dijkstra(Point start) {
        PriorityQueue<State> que = new PriorityQueue<>( Comparator.comparingInt( now -> now.cost ));
        int[][] ans = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(ans[i], Integer.MAX_VALUE);
        }

        que.offer(new State(start, 0));
        ans[start.y][start.x] = 0;

        while (!que.isEmpty()) {
            State now = que.poll();

            if (ans[now.point.y][now.point.x] < now.cost)
                continue;

            for (int i = 0; i < dx.length; i++) {
                int x = now.point.x + this.dx[i];
                int y = now.point.y + this.dy[i];

                if (isInIndices(x) && isInIndices(y)) {
                    int newCost = ans[now.point.y][now.point.x] + map[y][x];
                    if (ans[y][x] > newCost) {
                        ans[y][x] = newCost;
                        que.offer(new State(new Point(x, y), newCost));
                    }
                }
            }
        }

        return ans[size - 1][size - 1];
    }

    private boolean isInIndices(int num) {
        if (num < 0)
            return false;
        if (num >= size)
            return false;

        return true;
    }
}
