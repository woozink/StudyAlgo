package Bfs;
import java.io.*;
import java.util.*;


// TODO 비밀 번호의 안전거리 -> BFS (그 중 최단거리) 깊이, 거리를 잴 때 BFS를 활용할 수 있다.
public class 비밀번호제작 {
    static int n,m, depth;
    static boolean[] visited;
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // TODO 초기화
        visited = new boolean[n+1];
        depth = 0;
        q= new ArrayDeque<>();

        // TODO 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i < m; i++){
            int tmp = Integer.parseInt(st.nextToken());
            q.add(tmp);
            visited[tmp]= true;
        }
        
        bfs();
        
        

    }

    private static void bfs() {

        while(!q.isEmpty()){
            int size = q.size();
            depth++;

            while(size-- >0){
                int temp = q.poll();

                int x =1;
                while(x <= n){
                    if((x^temp) <= n && !visited[x^temp]){
                        visited[x^temp] =true;
                        q.add(x^temp);
                    }
                    x = x<<1;
                }
            }
        }
        System.out.println(depth -1);
    }

}
