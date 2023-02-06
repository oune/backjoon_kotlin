package swea;

import java.util.Arrays;
import java.util.Scanner;

public class S1289 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            char[] input = sc.nextLine().toCharArray();
            char[] now = new char[input.length];
            Arrays.fill(now, '0');

            int count = 0;
            for (int i = 0; i < input.length; i++) {
                if (input[i] != now[i]) {
                    count++;

                    for (int j = i; j < now.length; j++) {
                        now[j] = input[i];
                    }
                }
            }

            System.out.println("#" + test_case + " " + count);
        }
    }
}
