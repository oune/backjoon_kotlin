import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class S2001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= t; testcase++) {
            int[] numbs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = numbs[0];
            int m = numbs[1];

            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            } // input end

            int max = 0;
            for (int i = 0; i <= n - m; i++) {
                int endI = i + m;
                for (int j = 0; j <= n - m; j++) {
                    int endJ = j + m;

                    int sum = 0;
                    for (int k = i; k < endI; k++) {
                        for (int l = j; l < endJ; l++) {
                            sum += arr[k][l];
                        }
                    }
                    if (sum > max) {
                        max = sum;
                    }
                }

            }

            System.out.println("#" + testcase + " " + max);
        }
    }
}
