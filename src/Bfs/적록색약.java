package Bfs;

import java.io.*;
import java.util.*;

class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class 적록색약 {

    static int n;
    static Character[][] arr1, arr2;
    static boolean[][] visited1, visited2;
    static int answer1, answer2;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr1 = new Character[n][n];
        arr2 = new Character[n][n];
        visited1 = new boolean[n][n];
        visited2 = new boolean[n][n];


        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                arr1[i][j] = str.charAt(j);
                arr2[i][j] = str.charAt(j);

                if (str.charAt(j) == 'G') {
                    arr2[i][j] = 'R';
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (!visited1[i][j]) {
                    bfs(new Pos(i, j));
                    answer1++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (!visited2[i][j]) {
                    bfs2(new Pos(i, j));
                    answer2++;
                }
            }
        }


        System.out.println(answer1 + " " + answer2);

    }

    private static void bfs(Pos pos) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        visited1[pos.x][pos.y] = true;

        while(!q.isEmpty()){
            Pos pos2 = q.poll();
            for(int i =0; i < 4; i++){
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

                if(nx <0 ||nx>= n || ny <0 || ny>= n){
                    continue;
                }
                if(arr1[nx][ny] != arr1[pos.x][pos.y] || visited1[nx][ny]) {
                    continue;
                }

                q.offer(new Pos(nx, ny));
                visited1[nx][ny] = true;
            }
        }
    }

    private static void bfs2(Pos pos) {

        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        visited2[pos.x][pos.y] = true;

        while(!q.isEmpty()){
            Pos pos2 = q.poll();
            for(int i =0; i < 4; i++){
                int nx = pos2.x + dx[i];
                int ny = pos2.y + dy[i];

                if(nx <0 ||nx>= n || ny <0 || ny>= n){
                    continue;
                }
                if(arr2[nx][ny] != arr2[pos2.x][pos2.y] || visited2[nx][ny]) {
                    continue;
                }

                q.offer(new Pos(nx, ny));
                visited2[nx][ny] = true;
            }
        }
    }
}
