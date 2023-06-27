package Bfs;

import java.io.*;
import java.util.*;

public class 백조의호수 {
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Queue<Node> q; // 백조
    static Queue<Node> waterq;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][] visited;
    static Node[] swan;
    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        //TODO 초기화
        waterq = new LinkedList<>();
        q = new LinkedList<>();
        swan = new Node[2];
        map = new char[r][c];
        visited = new boolean[r][c];

        // TODO 입력

        int swanIdx = 0;
        for (int i = 0; i < r; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {

                map[i][j] = line[j];
                // 백조의 위치를 따로 저장
                if (map[i][j] == 'L') {
                    swan[swanIdx++] = new Node(i, j);
                }
                // 모든 물을 waterq에 저장
                if (map[i][j] != 'X') {
                    waterq.add(new Node(i, j));
                }
            }
        }

        bfs();
    }

    private static void bfs() {

        // 출발점이 되는 0번 백조
        q.add(swan[0]);
        visited[swan[0].x][swan[0].y] = true;

        int day = 0;
        boolean meet = false;

        while (true) {
            Queue<Node> nextQ = new LinkedList<>();

            while (!q.isEmpty()) {


                Node node = q.poll();

                // 백조를 만나면 종료 하는 조건문
                if (node.x == swan[1].x && node.y == swan[1].y) {
                    meet = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {

                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];

                    if (nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny]) {
                        continue;
                    }

                    visited[nx][ny] = true;

                    //물에 인접한 얼음으로 다음날 백조가 탐색할 지역
                    if (map[nx][ny] == 'X') {
                        nextQ.offer(new Node(nx, ny));
                        continue;
                    }

                    // 현재 탐색 가능 지역
                    q.offer(new Node(nx, ny));
                }
            }

            //벡조가 만났으면 종료
            if (meet) break;

            // q를 다음날 탐색할 지역이 담긴 nextQ로 바꾼다.
            q = nextQ;

            // 얼음을 녹인다.
            int size = waterq.size();

            for (int x = 0; x < size; x++) {
                Node node = waterq.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];

                    if (nx >= r || nx < 0 || ny >= c || ny < 0) continue;

                    //물에 인접한 얼음을 발견하면 녹이고 다시 큐에 넣는다.
                    if (map[nx][ny] == 'X') {
                        map[nx][ny] = '.';
                        waterq.add(new Node(nx, ny));
                    }
                }
            }
            day++;
        }
        System.out.println(day);


    }
}
