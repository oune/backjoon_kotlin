package swea;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class S1954 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int testCase = sc.nextInt();
        for (int testCaseIdx = 1; testCaseIdx <= testCase; testCaseIdx++) {
            final int size = sc.nextInt();
            int[][] arr = new int[size][size];
            for (int[] row : arr) {
                Arrays.fill(row, 0);
            }

            Point now = new Point(0, 0);
            Direction direction = new Direction();
            for (int i = 1; i <= size * size; i++) {
                arr[now.y][now.x] = i;

                Point moved = direction.move(now);
                if (moved.isNotIndices(size) || arr[moved.y][moved.x] != 0) {
                    direction.turn();
                    moved = direction.move(now);
                }
                now = moved;
            }

            sb.append("#");
            sb.append(testCaseIdx);
            sb.append("\n");
            for (int[] row : arr) {
                for (int num : row) {
                    sb.append(num);
                    sb.append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIndices(int size) {
        if (x < 0)
            return false;
        if (y < 0)
            return false;
        if (x >= size)
            return false;
        if (y >= size)
            return false;

        return true;
    }

    public boolean isNotIndices(int size) {
        return !isIndices(size);
    }
}

class Direction {
    int dx;
    int dy;

    public Direction() {
        this.dx = 1;
        this.dy = 0;
    }

    public Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Point move(Point point) {
        return new Point(point.x + dx, point.y + dy);
    }

    public void turn() {
        if (dx == 1 && dy == 0) {
            this.dx = 0;
            this.dy = 1;
            return;
        }
        if (dx == 0 && dy == 1) {
            this.dx = -1;
            this.dy = 0;
            return;

        }
        if (dx == -1 && dy == 0) {
            this.dx = 0;
            this.dy = -1;
            return;

        }
        if (dx == 0 && dy == -1) {
            this.dx = 1;
            this.dy = 0;
            return;
        }
    }
}
