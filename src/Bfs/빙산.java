package Bfs;

import java.io.*;
import java.util.*;


public class 빙산 {
    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static Queue<Node> q = new LinkedList<>();
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int[][] arr;
    static boolean[][] visited;
    static int year;

    public static void main(String[] args) throws IOException {

        // TODO 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // TODO Solutiontion
        solution();


        // TODO 출력
        System.out.println(year);

    }

    private static void solution() {
        // 경과 시간
        year = 0;

        while (true) {

            int count = 0; // 빙산의 개수
            visited = new boolean[n][m]; // 초기화

            //빙산 개수를 세자
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }

            // 빙산 갯수 끝 그 다음은 ? => 조건 맞춰주기
            if (count == 0) {
                year = 0;
                break;
            } else if (count >= 2) {
                break;
            }

            // 빙산의 높이 감소 시켜야지. => 계속 똑같은게 아니잖아.
            meltIce();

            year++;
        }

    }

    private static void meltIce() {

        int[][] melted = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] > 0) {
                    int count = 0; // 주변 바다 칸의 개수

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        //범위에 벗어나면,
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                            continue;
                        }

                        // 장소가 바다이면
                        if (arr[nx][ny] != 0) {
                            continue;
                        }

                        count++;
                    }

                    melted[i][j] = Math.max(0, arr[i][j] - count);
                }
            }
        }
        arr = melted; // 빙산 업데이트
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];


                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (visited[nx][ny] || arr[nx][ny] == 0) {
                    continue;
                }

                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
}
