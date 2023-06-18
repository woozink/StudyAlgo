package Bfs;

import java.io.*;
import java.util.*;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class 그림 {


    static Queue<Node> q;
    static int dx[] = {-1, 1, 0, 0}; // 상하좌우
    static int dy[] = {0, 0, -1, 1};
    static int n, m;
    static int arr[][];
    static boolean visited[][];
    static int drawcount = 0;
    static int maxDrawSize = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }


    }

    public static int bfs(int x, int y) {
        q= new LinkedList<>();
        q.offer(new Node(x,y));

        int count = 0;

        while(!q.isEmpty()){
            Node node = q.poll();

            count ++;

            for(int dir = 0; dir < 4; dir++){
                int nx = node.x + dx[dir];
                int ny = node.y+ dy[dir];

                if(nx < 0 || nx >= n || ny < 0 || ny>=m) continue;

                if(visited[nx][ny] || arr[nx][ny] == 0){
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }

            drawcount++;

            if(count > 1){
                count --;
            }
            maxDrawSize = Math.max(maxDrawSize , count );
        }


    }


}
