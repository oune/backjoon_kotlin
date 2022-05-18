package b7000;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCase; i++) {
            int calCount = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

            for (int j = 0; j < calCount; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String operator = st.nextToken();
                int operand = Integer.parseInt(st.nextToken());

                if (operator.equals("I")) {
                    minHeap.add(operand);
                    maxHeap.add(operand);
                } else if (!minHeap.isEmpty() && operator.equals("D")) {
                    if (operand == 1) {
                        minHeap.remove(maxHeap.poll());
                    } else if (operand == -1) {
                        maxHeap.remove(minHeap.poll());
                    }
                }
            }

            if (!maxHeap.isEmpty()) {
                bw.append(maxHeap.poll().toString());
                bw.append(" ");
                bw.append(minHeap.poll().toString());
                bw.append("\n");
            } else
                bw.append("EMPTY\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
