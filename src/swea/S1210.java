import java.util.Scanner;

public class S1210 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < 10; k++) {
            int testCase = sc.nextInt();

            int[][] arr = new int[100][100];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int x = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[99][i] == 2)
                    x = i;
            }

            for (int i = 98; i > 0; i--) {

                int left = x - 1;
                if (0 < left && left < arr.length) {
                    if (arr[i][left] == 1) {
                        while (0 <= left && arr[i][left] == 1) {
                            left--;
                        }
                        x = left + 1;
                        continue;
                    }
                }

                int right = x + 1;
                if (0 < right && right < arr.length) {
                    if (arr[i][right] == 1) {
                        while (right <= 99 && arr[i][right] == 1) {
                            right++;
                        }
                        x = right - 1;
                        continue;
                    }
                }
                arr[i][x] = 7;

            }


            sb.append("#");
            sb.append(testCase);
            sb.append(" ");
            sb.append(x);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
