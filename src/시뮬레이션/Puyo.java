package 시뮬레이션;

import java.io.*;
import java.util.*;

public class Puyo {

    static class Dot {
        int x;
        int y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int n = 12;
    static int m = 6;
    static char[][] map;
    static boolean[][] visited;
    static int crush_count = 0;
    static int total = 0;
    static ArrayList<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            char[] str = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = str[j];
            }
        }


        while (true) {
            visited = new boolean[n][m]; // 지속적으로 초기화를 한다.
            crush_count = 0;
            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        bfs(new Dot(i, j));
                    }
                }
            }
            if (crush_count == 0) {
                break;
            } else {
                total++;
            }
            down();
        }

        System.out.println(total);
    }

    private static void down() {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (map[i][j] != '.') {
                    goGround(i, j);
                }
            }
        }
    }

    private static void goGround(int a, int b) {

        int t = -1;

        for (int i = n - 1; i > a; i--) {
            if (map[i][b] == '.') {
                t = i;
                break;
            }
        }
        if (t != -1) {
            char tmp = map[a][b];
            map[a][b] = map[t][b];
            map[t][b] = tmp;
        }

    }

    private static void bfs(Dot d) {

        char check = map[d.x][d.y];
        int count = 0;

        Queue<Dot> q = new LinkedList<>();
        ArrayList<Dot> save = new ArrayList<>();
        visited[d.x][d.y] = true;
        q.add(d);

        while (!q.isEmpty()) {
            Dot t = q.poll();
            save.add(t);
            count++;

            for (int i = 0; i < 4; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == check && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Dot(nx, ny));
                }
            }
        }

        // 연결된 것이 4개가 넘으면 save의 뿌요들을 .으로 바꾸고 crush_count를 증가시킨다.
        if (count >= 4) {
            crush_count++;
            for (int i = 0; i < save.size(); i++) {
                Dot tmp = save.get(i);
                map[tmp.x][tmp.y] = '.';
            }
        }
    }


}
