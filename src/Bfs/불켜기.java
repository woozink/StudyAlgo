package Bfs;

import java.io.*;
import java.util.*;
/*
각 방에서 상하좌우로 이동이 가능한데, 중요한 것은 불이  켜져있는 방으로만 들어갈 수 있다.
-> 불을 켤 수 있는 방의 최대 개수

-> 최대한 많이 이동하여 방을 방문해서 불을 많이 켜야한다.

시작점은 무조건 불이 켜져있다. 개수 +1
방문하지 못하는 방이더라도 불을 키면 해당 방을 count 할 수 있다. 개수 +1

import 각 방에서 킬 수 있는 방은 인접한 방이 아니라 거리에 상관 없는 방도 가능하다.

이는 지금까지 지나왔던 방들 중에 인접한 방에 불이 켜질 수도 있다.

BFS 알고리ㅡㅈㅁ 중 지나 왔던 방이 없을 때 까지 1,1 에서 시작하는 BFS 알고리즘을 반복해서 수행한다.

그래프를 그리기 위해 기존 문제들은 보통 하나의 Vertex에서 다른 하나의 Vertex로 이동하기 때문에 1차원 Arra
 */


public class 불켜기 {

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static boolean[][] switched, visited; // boolean 으로
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Node>[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());
        switched = new boolean[n][n]; // true : 불킴 / false : 불끔
        visited = new boolean[n][n];    // 이미 방을 방문해서 불을 켰는지 안켰는지 체크

        // 2차원 ArrayList 배열 사용
        // 초기화
        graph = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = new ArrayList<Node>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.valueOf(st.nextToken()) - 1;
            int y = Integer.valueOf(st.nextToken()) - 1;
            int a = Integer.valueOf(st.nextToken()) - 1;
            int b = Integer.valueOf(st.nextToken()) - 1;

            // 그래프 생성
            Node endPoint = new Node(a, b);
            graph[y][x].add(endPoint);


        }
        int count = bfs() + 1;

        System.out.println(count);

    }

    private static int bfs() {
        int count = 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));


        // 방문 초기화
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }

        switched[0][0] = true;
        visited[0][0] = true;

        // 한번 시작해서 BFS 탐색하는 경우 전부 탐색
        // 불 킨적이 없을 때까지 BFS 함수 반복

        boolean turnOn = false;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (Node neighborPoint : graph[node.y][node.x]) {
                if(!switched[neighborPoint.y][neighborPoint.x]){

                    switched[neighborPoint.y][neighborPoint.x] = true;
                    count++;
                    turnOn = true;
                }
            }


            // 상하 좌우 이동 가능한지 ?
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                //불이 켜져 있거나 방문 기록이 있다면
                if (!switched[ny][nx] || visited[ny][nx]) {
                    continue;
                }

                q.offer(new Node(nx, ny));
                visited[ny][nx] = true;

            }
        }
        if (turnOn) {
            count += bfs();
        }
        return count;
    }

}
