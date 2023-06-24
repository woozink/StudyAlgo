package Bfs;

import java.io.*;
import java.util.*;


public class 숨바꼭질3 {
    static class Node {
        int x;
        int time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;

        }
    }


    static int n, k;
    static Queue<Node> q = new LinkedList<>();
    static boolean[] visited;
    static int max = 100000;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        visited = new boolean[max+1];
        bfs();

        System.out.println(min);
    }

    private static void bfs() {
        q.add(new Node(n, 0));

        while (!q.isEmpty()) {
            Node current = q.poll();
            visited[current.x] = true;
            if(current.x == k) min = Math.min(min, current.time);


            // 순간 이동할 때
            if(current.x *2 <= max && visited[current.x *2] == false){
                q.add(new Node(current.x *2, current.time));
            }

            // 앞으로 1 전진
            if(current.x +1 <= max && visited[current.x +1] == false){
                q.add(new Node(current.x+1, current.time+1));
            }
            // 뒤로 1 전진
            if(current.x -1 >=0 && visited[current.x -1] == false){
                q.add(new Node(current.x-1, current.time+1));
            }

        }
    }

}
