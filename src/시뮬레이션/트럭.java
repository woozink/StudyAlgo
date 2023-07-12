package 시뮬레이션;

import java.io.*;
import java.util.*;

public class 트럭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 다리를 건너는 트럭의 수
        int w = Integer.parseInt(st.nextToken()); // 다리의 길이
        int l = Integer.parseInt(st.nextToken()); // 다리의 최대 하중

        Queue<Integer> truck = new LinkedList<>();
        Queue<Integer> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truck.add(Integer.parseInt(st.nextToken()));
        }


        int time = 0;
        int bridgeWeight = 0;

        for (int i = 0; i < w; i++) {
            q.add(0);
        }

        while (!q.isEmpty()) {
            // 1초가 지나고 다리에 차가 올라갈 수 있으니, time에 하나를 더 해주고
            time++;
            // 이렇게 나온 무게는 다리 밖으로 나오므로, bw(다리의 무게)dㅔ서 내려온 만큼 q.poll을 빼준다.
            // 처음 다리의 길이까지는 0만빠진다.
            bridgeWeight -= q.poll();
            // 그리고 만약 트럭이 남아 있다면
            if (!truck.isEmpty()) {
                // 가자 앞에 트럭을 bw에 더해도 최대하중 보다 작아서 다리가 견딜 수 있는지 검사하고
                if (truck.peek() + bridgeWeight <= l) {
                    // 만약 작다면 건널 수 있으므로 bw에 무게를 더해주고
                    bridgeWeight += truck.peek();
                    // 다리위에 q에도 트럭의 무게 만큼을 더하고 first out을 해준다.
                    q.add(truck.poll());
                } else {
                    // 만약 무게를 버틸 수 없다면 다리위 (q)에는 아무것도 안실리므로 q 에 0을 넣어준다.
                    q.add(0);
                }
            }
        }
        System.out.println(time);


    }

}
