package Bfs;

import java.io.*;
import java.util.*;
/*
수빈은 2초에서 12 자리에 가서 방문 처리를 하게 되면 동생이 3초에 12에 도달하면서 12의 방문처리가 되어 있기 때문에 3초를 반환하게 되는데
문제의 조건에 의해 2초 때 12위치에서 3초 때 12 위치로 이동할 방법이 없으므로 틀리게 된다.
수빈이 t초에서 위치가 p라고 가정할 때 t+1초에서 위치는 p일 수가 없다.

하지만 t+2초에서는 pdlf tnrk dlTek. (t -> t-1 -> t 혹은 t -> t+1 -> t)
한번 방문했던 곳을 2초가 있음녀 다시 방문할 수 있게 된다.
수빈이 동생을 순간이동을 해서 앞질러서 동생이 도착할 곳에 먼저 도착하고, 그 자리를 2초 간격으로 유지할 수 있다는 것을 의미한다.

수빈이 p위치에 도착하는 t초가 짝수이면 짝수 시간 때 마다 p위치로 돌아올 수 있고
p위치에 도착하는 t초가 홀수이면 홀수 시간 때마다 p위치로 돌아올 수 있다.

 */
public class 숨바꼭질5 {
    static int n, k;
    static boolean[][] visited = new boolean[500001][2]; // 방문 2차원 배열 50만개

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n == k) {
            System.out.println(0);
        } else {
            System.out.println(bfs(n));
        }

    }

    private static int bfs(int start) {

        int time = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        visited[start][time] = true;


        while (!q.isEmpty()) {
            // k를 벗어나는 경우 예외 경우
            if (k > 500000) {
                return -1;
            }

            // time의 짝수 홀수인지 판별
            int newTime = time % 2;

            // 만난 경우(짝수 홀수)
            if (visited[k][newTime]) {
                return time;
            }

            // 현재 q의 사이즈 만큼만 돌리기 - 시간 계산

            for (int j = 0, size = q.size(); j < size; j++) {

                int now = q.poll();

                //다음 이동의 짝수 홀수 여부
                int nextTime = (time + 1) % 2;
                int next;

                // 다음에서 역역을 방문처리
                next = now - 1;
                if (next >= 0 && !visited[next][nextTime]) {
                    visited[next][nextTime] = true;
                    q.add(next);
                }


                next = now + 1;
                if (next < 500001 && !visited[next][nextTime]) {
                    visited[next][nextTime] = true;
                    q.add(next);
                }

                next = now * 2;
                if (next < 500001 && !visited[next][nextTime]) {
                    visited[next][nextTime] = true;
                    q.add(next);
                }

            }
            time++;
            k += time;

        } // while
        return -1;
    }

}
