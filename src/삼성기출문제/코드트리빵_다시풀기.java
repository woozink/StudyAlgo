package 삼성기출문제;

import java.util.*;
import java.io.*;

public class 코드트리빵_다시풀기 {
    static int INT_MAX = Integer.MAX_VALUE;
    static Pair EMPTY = new Pair(-1, -1);
    static int DIR_NUM = 4;
    static int MAX_N = 15;
    static int MAX_M = 30;

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isSame(Pair p) {
            return this.x == p.x && this.y == p.y;

        }
    }

    static int n, m;
    static int[][] grid = new int[MAX_N][MAX_N];
    static Pair[] cuList = new Pair[MAX_M];
    static Pair[] people = new Pair[MAX_M];
    static int currT;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int step[][] = new int[MAX_N][MAX_N];
    static boolean[][] visited = new boolean[MAX_N][MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

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

        for (int i = 0; i < m; i++) {
            people[i] = EMPTY;
        }

        // 1분에 한번씩 시뮬
        while (true) {
            currT++;
            simulation();

            if (end()) break;
        }

        System.out.print(currT);
    }

    private static boolean end() {
        //단 한사람이라도 편의점에 도착 안하면 false
        for (int i = 0; i < m; i++) {
            if (!people[i].isSame(cuList[i])) {
                return false;
            }
        }
        return true;
    }

    private static void simulation() {
        // step 1 : 격자에 있는 사람들에 한에서 1칸 움직인다.
        for (int i = 0; i < m; i++) {
            // 기저 : 범위에 없거나, 편의점에 도착한 사람 빼기
            if (people[i] == EMPTY || people[i].isSame(cuList[i])) {
                continue;
            }

            // 역BFS
            bfs(cuList[i]);

            int px = people[i].x;
            int py = people[i].y;

            int minDist = Integer.MAX_VALUE;
            int minX = -1;
            int minY = -1;

            for (int j = 0; j < DIR_NUM; j++) {
                int nx = px + dx[j];
                int ny = py + dy[j];
                if (inRange(nx, ny) && visited[nx][ny] && minDist > step[nx][ny]) {
                    minDist = step[nx][ny];
                    minX = nx;
                    minY = ny;
                }
            }
            people[i] = new Pair(minX, minY);
        }
        // step 2 : 편의점 도착한 사라들 못 움직이게 바꾼다.
        for(int i = 0; i < m; i++){
            if(people[i].isSame(cuList[i])){
                grid[people[i].x][people[i].y] =2;
            }
        }
        // step 3 : 베이스 캠프 조건에 맞게 이동시킨다.
        if(currT > m){
            return;
        }

        int minDist= Integer.MAX_VALUE;
        int minX = -1;
        int minY = -1;

        for(int i = 0; i < n; i++){
            for(int j = 0; j< n; j++){
                if(visited[i][j] && grid[i][j] ==1 && minDist > step[i][j]){
                    minDist = step[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }

        people[currT =1 ] = new Pair(minX, minY);

        grid[minX][minY] = 2;



    }

    private static boolean inRange(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n;
    }

    // step 은 최단거리 결과 기록
    private static void bfs(Pair firstPosition) {
        // visited step 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
                step[i][j] = 0;
            }
        }
        // 초기 위치 설정
        Queue<Pair> q = new LinkedList<>();
        int sx = firstPosition.x;
        int sy = firstPosition.y;
        q.add(firstPosition);
        visited[sx][sy] = true;
        step[sx][sy] = 0;

        // BFS 시작
        while (!q.isEmpty()) {
            Pair currPos = q.poll();

            // 인접한 칸을 보며 아직 방문하지 않은 칸을 큐에 넣어준다.
            int x = currPos.x;
            int y = currPos.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(canGo(nx, ny)){
                    visited[nx][ny] = true;
                    step[nx][ny] = step[x][y]+1;
                    q.add(new Pair(nx, ny));
                }

            }
        }
    }

    private static boolean canGo(int nx, int ny) {
        return inRange(nx,ny) && !visited[nx][ny] && grid[nx][ny] != 2;
    }
}
