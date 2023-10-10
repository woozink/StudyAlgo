package 삼성기출문제;

import java.util.*;
import java.io.*;

public class 코드트리빵_다시풀기 {

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isSame(Pair p) {
            return this.x == p.x && this.y == p.y;

        }
    }

    static int n, m;
    static int[][] grid = new int[n][n];
    static Pair[] cuList = new Pair[m];
    static Pair[] people = new Pair[m];
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int currT;
    static Pair EMPTY = new Pair(-1, -1);
    static int[][] step = new int[n][n];
    static boolean[][] visited = new boolean[n][n];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][n];
        cuList = new Pair[m];
        people = new Pair[m];
        step = new int[n][n];
        visited = new boolean[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            cuList[i] = new Pair(x - 1, y - 1);
        }

        // 초기 사람들은 격자 밖에 있으므로 위치를 Empty
        for (int i = 0; i < m; i++) {
            people[i] = new Pair(-1, -1);
        }

        while (true) {
            currT++;
            simulate();
            // 전부 끝나면 종료
            if (end()) break;
        }
        System.out.print(currT);

    }

    private static boolean end() {
        // 편의점에 한명이라도 도달 못하면 안끝난 거다.
        for (int i = 0; i < m; i++) {
            if (!people[i].isSame(cuList[i])) return false;
        }
        return true;
    }

    private static void simulate() {
        // step 1 격자에 있는 모든 사람들에 한하여 편의점 방향을 향해 1칸 움직인다. 
        for (int i = 0; i < m; i++) {
            // 아직 격자 밖에 있는 사람이거나, 편의점에 도착한 사람이라면 패스한다. 
            if (people[i] == EMPTY || people[i].isSame(cuList[i])) continue;

            // 원래는 현재 위치에서 편의점 위치까지 최단거리를 구해야한다.
            // 다만 최단 거리가 되기 위한 그 다음 위치를 구하기 위해서는 
            // 거꾸로 편의점 위치를 시작으로 현재 위치까지 오는 최단거리를 구해주어야한다.
            // 따라서 편의점 위치를 시작으로하는 BFS를 진행한다.

            bfs(cuList[i]);

            int px = people[i].x, py = people[i].y;

            // 현재 위치에서 상좌우하 중 최단 거리 값이 가장 작은 곳을 고르면,
            // 그 곳으로 이동하는 것이 최단 거리대로 이동하는 것이된다.
            // 그러한 위치 중 상좌우하 우선 순위 대로 가장 적절한 곳을 골라준다.
            int minDist = Integer.MAX_VALUE;
            int minX = -1;
            int minY = -1;
            for (int j = 0; j < 4; j++) {
                int nx = px + dx[j];
                int ny = py + dy[j];
                if (inRange(nx, ny) && visited[nx][ny] && minDist > step[nx][ny]) {
                    minDist = step[nx][ny];
                    minX = nx;
                    minY = ny;
                }
            }
            // 우선순위가 가장 높은 위치로 한칸
            people[i] = new Pair(minX, minY);
        }
        // step 2 편의점에 도착한 사람에 한하여 앞으로 불가능 하다는 표시로 grid를 2로 변경
        for (int i = 0; i < m; i++) {
            if (people[i].isSame(cuList[i])) {
                int px = people[i].x;
                int py = people[i].y;
                grid[px][py] = 2;
            }
        }
        // step3 현재 시간 currT에 대해 currT <= m을 만족하면, t번 사람이 베켐으로 간다.

        // currT가 m보다 높으면 패스
        if (currT > m) {
            return;
        }

        // 3-1 편의점으로 부터 가장 가까운 베이스 캠프를 고르기 위해 편의접을 시작하는 BFS를 진행한다.
        bfs(cuList[currT - 1]);

        // 3-2 편의접에서 가장 가까운 베이스 캠프를 선택한다. i ,j 가 증가하는 순으로 들르기 떄문에 가장 가까운
        // 베이스 캠프가 여러가지여도 행 열 우선 순위 대로 골라진다.
        int minDist = Integer.MAX_VALUE;
        int minX = -1;
        int minY = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //방문가능한 베이스캠프 중 거리가 가장 가까운 위치를 찾는다.
                if (visited[i][j] && grid[i][j] == 1 && minDist > step[i][j]) {
                    minDist = step[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }
        // 베캠으로 이동
        people[currT - 1] = new Pair(minX, minY);

        // 해당 베이스 캠프는 앞으로 이동이 불가능한 칸임을 표신한다.
        grid[minX][minY] = 2;

    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean canGo(int x, int y) {
        return inRange(x, y) && !visited[x][y] && grid[x][y] != 2;
    }

    // startPos를 시작으로 하는 BFS
    // 시작점으로 부터 최단거리 결과는 step 배열에 기록된다.
    private static void bfs(Pair startPos) {
        //visited, step 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
                step[i][j] = 0;
            }
        }

        // 초기위치
        Queue<Pair> q = new LinkedList<>();
        int sx = startPos.x;
        int sy = startPos.y;
        q.add(startPos);
        visited[sx][sy] = true;
        step[sx][sy] = 0;

        // BFS 시작
        while (!q.isEmpty()) {
            // 가장 앞 원소 선택
            Pair currPos = q.poll();

            // 인접한 칸을 보며 아직 방문하지 않은 칸을 큐에 넣어준다.
            int x = currPos.x;
            int y = currPos.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 갈 수 있는 경우에만 집중
                if(canGo(nx,ny)){
                    visited[nx][ny] = true;
                    step[nx][ny] = step[x][y] +1;
                    q.add(new Pair(nx,ny));
                }
            }
        }


    }
}
