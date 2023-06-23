package Bfs;

import java.io.*;
import java.util.*;

class Kal {
    int l;
    int r;
    int c;
    int count;

    protected Kal(int l, int r, int c, int count) {
        this.l = l;
        this.r = r;
        this.c = c;
        this.count = count;

    }
}

public class 상범빌딩 {
    static int l, r, c;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dl = {0, 0, 0, 0, -1, 1};
    static char[][][] arr;
    static boolean[][][] visited;
    static Kal s, e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            l = Integer.parseInt(st.nextToken()); // 층수
            r = Integer.parseInt(st.nextToken()); // i
            c = Integer.parseInt(st.nextToken()); // j

            if (l == 0 && r == 0 && c == 0) break;

            // 빌딩 정보 받는다.
            arr = new char[l][r][c];

            for (int x = 0; x < l; x++) {
                for (int i = 0; i < r; i++) {
                    String str = br.readLine();
                    for (int j = 0; j < c; j++) {
                        char c = str.charAt(j);
                        arr[x][i][j] = c;

                        if (c == 'S') {
                            s = new Kal(x, i, j, 0);
                        } else if (c == 'E') {
                            e = new Kal(x, i, j, 0);
                        }
                    }
                }
                br.readLine();
            }
            visited = new boolean[l][r][c];
            int result = bfs();
            if (result == -1) System.out.println("Trapped!");
            else System.out.println("Escaped in " + result + " minute(s).");
        }
    }

    private static int bfs() {
        Queue<Kal> q = new LinkedList<>();
        visited[s.l][s.r][s.c] = false;
        q.offer(new Kal(s.l, s.r, s.c, 0));


        while (!q.isEmpty()) {

            Kal current = q.poll();

            if (current.l == e.l && current.r == e.r && current.c == e.c) return current.count;

            for (int i = 0; i < 6; i++) {
                int nl = current.l + dl[i];
                int nr = current.r + dx[i];
                int nc = current.c + dy[i];

                if (nl < 0 || nl >= l || nr < 0 || nr >= r || nc < 0 || nc >= c) {
                    continue;
                }

                if (visited[nl][nr][nc] || arr[nl][nr][nc] == '#') {
                    continue;
                }

                visited[nl][nr][nc] = true;
                q.offer(new Kal(nl, nr, nc, current.count + 1));

            }
        }
        return -1;
    }
}
