package Bfs;

import java.io.*;
import java.util.*;
class Node{
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}


public class 불2 {
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static Queue<Node> fq;
    static Queue<Node> sq;
    static boolean[][] sVisited;
    static boolean[][] fVisited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            arr = new int[n][m];
            sVisited = new boolean[n][m];
            fVisited = new boolean[n][m];

            fq = new LinkedList<>();
            sq = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                String s = br.readLine();

                for (int j = 0; j < m; j++) {
                    arr[i][j] = s.charAt(j);

                    if (arr[i][j] == '@') {
                        arr[i][j] = '.';
                        sq.add(new Node(i, j));
                        sVisited[i][j] = true;
                    } else if (arr[i][j] == '*') {
                        fq.add(new Node(i, j));
                        fVisited[i][j] = true;
                    }

                }
            }

            int result = bfs();
            if (result == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(result);
            }


        }


    }

    private static int bfs() {
        int time = 0;

        while (!sq.isEmpty()) {
            time++;

            int sL = sq.size();
            int fL = fq.size();

            for (int i = 0; i < fL; i++) {
                Node fire = fq.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = fire.x + dx[j];
                    int ny = fire.y + dy[j];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    if (arr[nx][ny] == '#' || fVisited[nx][ny]) {
                        continue;
                    }

                    fVisited[nx][ny] = true;
                    arr[nx][ny] = '*';
                    fq.add(new Node(nx, ny));
                }
            }

            for (int i = 0; i < sL; i++) {
                Node san = sq.poll(); // 지훈이의 현재 위치

                for (int j = 0; j < 4; j++) {
                    int nx = san.x + dx[j];
                    int ny = san.y + dy[j];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        return time;
                    }

                    if (arr[nx][ny] != '.' || sVisited[nx][ny]) {
                        continue;
                    }


                    sVisited[nx][ny] = true;
                    sq.add(new Node(nx, ny));
                }
            }
        }
        return -1;
    }

}

