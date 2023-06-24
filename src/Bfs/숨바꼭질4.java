package Bfs;

import java.io.*;
import java.util.*;

public class 숨바꼭질4 {

    static int n, m;
    static Queue<Integer> q = new LinkedList<>();
    static Stack<Integer> stack = new Stack<>();
    static int[] time;
    static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        time = new int[1000001];
        prev = new int[1000001];
        Arrays.fill(time, -1); // 초기화

        bfs();


    }

    private static void bfs() {
        q = new LinkedList<>();
        stack = new Stack<>();

        q.offer(n); // 시작 위치
        time[n] = 0; // 시작하는 값 0초

        while (!q.isEmpty()) {
            int x = q.poll();

            if (x == m) {
                while (x != n) {
                    stack.push(x);
                    x = prev[x];
                }
                stack.push(n);
                break;
            }

            int[] dotX = {-1,1,x};

            for(int i = 0; i < 3; i++){
                int next = dotX[i] + x;
                if( next < 0 || next > 100000 || time[next] != -1) continue;
                time[next] = time[x]+1;
                prev[next] = x;
                q.offer(next);
            }
        }
        System.out.println(time[m]);
        while(!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }

    }
}
