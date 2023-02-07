

import java.util.PriorityQueue;
import java.util.Scanner;

public class S1208 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        for (int testcase = 1; testcase <= 10; testcase++) {
            PriorityQueue<Integer> minQue = new PriorityQueue<>();
            PriorityQueue<Integer> maxQue = new PriorityQueue<>( (a, b) -> b - a );

            final int dumps = sc.nextInt();

            for (int i = 0; i < 100; i++) {
                final int now = sc.nextInt();
                minQue.offer(now);
                maxQue.offer(now);
            }

            for (int i = 0; i < dumps; i++) {
                int min = minQue.poll();
                int max = maxQue.poll();

                maxQue.remove(min);
                minQue.remove(max);

                minQue.offer(min + 1);
                maxQue.offer(max - 1);
            }

            final int distance = maxQue.poll() - minQue.poll();
            sb.append("#");
            sb.append(testcase);
            sb.append(" ");
            sb.append(distance);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}

class dummy {

}

