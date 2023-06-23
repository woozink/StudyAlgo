package Bfs;

import java.io.*;
import java.util.*;

public class 텀프로젝트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());

            int arr[][] = new int[n + 1][];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n + 1; i++) {
                arr[i] = new int[]{i, Integer.parseInt(st.nextToken())};
            }

            System.out.println(bfs(arr));
        }
    }

    private static int bfs(int[][] arr) {
        boolean[] visited = new boolean[arr.length]; //방문배열
        int cnt = arr.length - 1;

        for (int i = 1; i < arr.length; i++) {
            Deque<int[]> q = new LinkedList<>();

            if (visited[i]) continue; // 이미 방문한 사람이면 다음 사람
            visited[i] = true;


            // 자기 자신 선택
            if (arr[i][0] == arr[i][1]) {
                cnt -= 1;
                continue;
            }


            // 자기 자식 선택 안한 경우
            // 큐 생성
            q.add(arr[i]);
            while (!q.isEmpty()) {
                int nextIdx = q.peekLast()[1];
                if (visited[nextIdx]) break;
                q.add(arr[nextIdx]);
                visited[nextIdx] = true;
            }

            // 생성된 큐 검사
            while (!q.isEmpty()) {
                if (q.peekFirst()[0] != q.peekLast()[1]) {
                    q.pollFirst();
                    continue;
                }

                cnt -= q.size();
                break;
            }
        }
        return cnt;
    }

}
