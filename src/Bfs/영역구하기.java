package Bfs;

import java.io.*;
import java.util.*;

//class Node {
//    int x;
//    int y;
//
//    public Node(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}

public class 영역구하기 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int m, n, k;
//    static int lx, ly;
//    static int rx, ry;
    static int count = 0;

    static int[][] arr;
    static boolean[][] visited;
    static ArrayList<Integer> areas;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[m][n];
        visited = new boolean[m][n];


        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());

            for (int x = lx; x < rx; x++) {
                for (int y = ly; y < ry; y++) {
                    arr[y][x] = 1;
                }
            }
        }

        areas = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    if (arr[i][j] == 0 && !visited[i][j]) {
                        count++;
                        areas.add(bfs(i,j));

                    }
                }

            }
        }

        Collections.sort(areas);

        System.out.println(count);
        for(int area : areas){
            System.out.print(area + " ");
        }

    }

    private static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(x, y));
        visited[x][y] = true;
        int area = 1;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }

                if (arr[nx][ny] != 0 || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new Node(nx, ny));
                area++;
            }
        }
        return area;
    }
}
