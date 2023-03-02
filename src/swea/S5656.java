import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class S5656 {
    static int[][] origin;
    static int[][] arr;
    static int min;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int tIdx = 1; tIdx <= tc; tIdx++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int dropCnt = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            origin = new int[height][width];
            for (int i = 0; i < height; i++) {
                StringTokenizer line = new StringTokenizer(br.readLine(), " ");

                for (int j = 0; j < width; j++) {
                    origin[i][j] = Integer.parseInt(line.nextToken());
                }
            }

            selected = new int[dropCnt];
            Arrays.fill(selected, 0);
            min = Integer.MAX_VALUE;
            perm(0);

            sb.append("#");
            sb.append(tIdx);
            sb.append(" ");
            sb.append(min);
            sb.append('\n');
        }

        System.out.println(sb);
    }
    
    private static int top(int pos) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][pos] != 0)
                return i;
        }
        return arr.length;
    }
    private static void bomb(int x, int y) {
        if (y < 0)
            return;
        if (y >= arr.length)
            return;
        if (x < 0)
            return;
        if (x >= arr[y].length)
            return;

        int size = arr[y][x];
        arr[y][x] = 0;

        bomb(x, y, 1, 0, size - 1);
        bomb(x, y, -1, 0, size - 1);
        bomb(x, y, 0, 1, size - 1);
        bomb(x, y, 0, -1, size - 1);
    }

    private static void bomb(int x, int y, int dx, int dy, int size) {
        int nx = x;
        int ny = y;
        for (int i = 0; i < size; i++) {
            nx += dx;
            ny += dy;
            if (ny < 0)
                return;
            if (ny >= arr.length)
                return;
            if (nx < 0)
                return;
            if (nx >= arr[ny].length)
                return;

            bomb(nx, ny);
        }
    }

    private static void drop() {
        for (int j = 0; j < arr[0].length; j++) {
            int last;
            for (last = arr.length - 1; last >= 0; last--) {
                if (arr[last][j] == 0)
                    break;
            }

            int i = last - 1;
            while(i >= 0) {
                if (arr[i][j] != 0) {
                    arr[last][j] = arr[i][j];
                    arr[i][j] = 0;

                    for (; last >= 0; last--) {
                        if (arr[last][j] == 0)
                            break;
                    }
                }

                i--;
            }
        }
    }

    private static void printArr() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] selected;
    private static void perm(int depth) {
        if (depth == selected.length) {
            arr = new int[origin.length][origin[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++){
                    arr[i][j] = origin[i][j];
                }
            }

            for (int dropOrder = 0; dropOrder < selected.length; dropOrder++) {
                int dropPoint = selected[dropOrder];
                int top = top(dropPoint);
                bomb(dropPoint, top);
                drop();
            }
            int bricks = count();
            if (min > bricks)
                min = bricks;
            return;
        }

        for (int i = 0; i < origin[0].length; i++) {
            selected[depth] = i;
            perm(depth + 1);
        }
    }

    private static int count() {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0)
                    count++;
            }
        }
        return count;
    }
}
