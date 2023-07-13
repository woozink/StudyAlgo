package 시뮬레이션;

import java.io.*;
import java.util.*;

public class Maaaaaaaaaze {

    static class Node {
        int r;
        int c;
        int z;
        int cnt;

        public Node(int r, int c, int z, int cnt) {
            this.r = r;
            this.c = c;
            this.z = z;
            this.cnt = cnt;
        }
    }

    static int[][][] map;
    static int[][][] copy;
    static int ans = Integer.MAX_VALUE;
    static int[] dir;
    static int[] order;
    static boolean[] check;
    static boolean[][][] visited;
    static int dr[] = {-1, 1, 0, 0, 0, 0};
    static int dc[] = {0, 0, -1, 1, 0, 0};
    static int dz[] = {0, 0, 0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5][5]; // 5 * 5 * 5
        dir = new int[5];
        order = new int[5];
        check = new boolean[5];


        // 입력 받을 5개의 판에 대한 정보를 받아옴
        for (int z = 0; z < 5; z++) {
            for (int r = 0; r < 5; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 5; c++) {
                    map[z][r][c] = Integer.parseInt(st.nextToken());
                }
            }
        }

        perm(0);

        System.out.println((ans == Integer.MAX_VALUE ? -1 : ans));

    }


    // 5개의 판 중에서 쌓을 순서를 정한다.
    private static void perm(int idx) {
        if (idx == 5) {
            copy = new int[5][5][5];
            for (int i = 0; i < order.length; i++) {
                lotation(0); // 각 판을 회전시켜서 쌓은 미로를 생성
            }
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!check[i]) {
                check[i] = true;
                order[idx] = i;
                perm(idx + 1);
                check[i] = false;
            }
        }


    }

    // 4방향 중 회전
    private static void lotation(int idx) {
        if (idx == 5) {
            for (int i = 0; i < order.length; i++) {
                int pan = order[i];
                int d = dir[pan];
                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 5; c++) {
                        if (d == 0) {
                            copy[i][r][c] = map[pan][r][c];
                        }
                        if (d == 1) {
                            copy[i][c][4 - r] = map[pan][r][c];
                        }
                        if (d == 2) {
                            copy[i][4 - r][4 - c] = map[pan][r][c];
                        }
                        if (d == 3) {
                            copy[i][4 - c][r] = map[pan][r][c];
                        }
                    }
                }
                if (copy[0][0][0] == 1 && copy[4][4][4] == 1) {
                    bfs(); // 시작 지점과 도착 지점이 1로 연결되어 있는 경우 BFS 수행
                }
            }
            return;
        }
        for (int d = 0; d < 4; d++) {
            dir[idx] = d;
            lotation(idx + 1);
        }
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        visited = new boolean[5][5][5];
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.r == 4 && node.c == 4 && node.z == 4) {
                ans = Math.min(ans, node.cnt);
                if (ans == 12) {
                    System.out.println(12);
                    System.exit(0);
                }
                break;
            }

            for (int d = 0; d < 6; d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];
                int nz = node.z + dz[d];

                if (nr < 0 || nc < 0 || nz < 0 || nr >= 5 || nc >= 5 || nz >= 5 ||
                        visited[nz][nr][nc] || copy[nz][nr][nc] == 0) {
                    continue;
                }

                visited[nz][nr][nc] = true;
                q.add(new Node(nr, nc, nz, node.cnt + 1));

            }
        }
    }
}
