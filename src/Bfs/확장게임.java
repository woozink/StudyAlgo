package Bfs;

import java.io.*;
import java.util.*;

public class 확장게임 {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, p;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[] player;
    static int[][] map;

    static Queue<Node> q[];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        // TODO 1. 초기화
        player = new int[p + 1];
        map = new int[n][m];

        // TODO 2. 플레이어 별로 탐색할 큐 만들기
        q = new LinkedList[p + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= p; i++) {
            player[i] = Integer.parseInt(st.nextToken());
            q[i] = new LinkedList<>();
        }

        // TODO 3. 입력 받기

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char tmp = str.charAt(j);

                // map에 입력 된 것이 성이라면
                if (tmp >= '1' && tmp <= '9') {
                    map[i][j] = tmp - '0';
                    q[map[i][j]].add(new Node(i, j));
                }

                // 벽이면 -1로 치환해서 넣기
                else if (tmp == '#') {
                    map[i][j] = -1;
                }

                // 갈 수 있는 통로면 0으로 치환해서 넣기
                else if (tmp == '.') {
                    map[i][j] = 0;
                }
            }
        }


        // TODO 4. SOL 시작
        // TODO 5. BFS를 SIZE로 묶을 때 해당플레이어를 움질일 수 있을 만큼 반복
        boolean flag = false;

        while (!flag) {
            // 플레이어 큐가 비면 종료를 시킨다.
            int cnt = 0;

            for (int i = 1; i <= p; i++) {
                if (q[i].size() == 0) cnt++;
            }

            if (cnt == p) {
                flag = true;
                break;
            }
            bfs();
        }

        // TODO 7. 마지막 출력

        int result[] = new int[p + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] >= 1 && map[i][j] <= 9) {
                    result[map[i][j]]++;
                }
            }
        }

        for (int i = 1; i <= p; i++) {
            System.out.print(result[i] + " ");
        }

    }

    private static void bfs() {
        for (int i = 1; i <= p; i++) {

            if (q[i].isEmpty()) continue;

            // 횟수 만큼 이동
            for (int play = 0; play < player[i]; play++) {
                int qsize = q[i].size();

                if (qsize == 0) break;

                while (qsize-- > 0) {
                    Node node = q[i].poll();

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = node.x + dx[dir];
                        int ny = node.y + dy[dir];

                        if (nx >= 0 && ny >= 0 && nx < n && ny <m && map[nx][ny] == 0) {
                            map[nx][ny] = i;
                            q[i].add(new Node(nx, ny));
                        }
                    }
                }
            }
        }
    }
}
