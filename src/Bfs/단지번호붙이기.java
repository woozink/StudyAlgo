package Bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class 단지번호붙이기 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int n;
    static int count = 0;

    static int[][] arr;
    static boolean[][] visited;
    static ArrayList<Integer> areas;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());


        arr = new int[n][n];
        visited = new boolean[n][n];


        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j)-'0';
            }
        }


        areas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    count++;
                    areas.add(bfs(i, j));
                }
            }
        }

        Collections.sort(areas);

        System.out.println(count);
        for (int area : areas) {
            System.out.println(area);
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

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (arr[nx][ny] != 1 || visited[nx][ny]) {
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
