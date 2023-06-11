package Queue;
import java.io.*;
import java.util.*;
public class 큐 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int last = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String x = st.nextToken();

            if(x.equals("push")){
                last = Integer.parseInt(st.nextToken());
                q.offer(last);
            }

            else if(x.equals("pop")){
                if(!q.isEmpty()){
                    sb.append(q.poll()).append('\n');
                }else{
                    sb.append(-1).append('\n');
                }
            }

            else if(x.equals("size")){
                sb.append(q.size()).append('\n');
            }

            else if(x.equals("empty")){
                if(!q.isEmpty()){
                    sb.append(0).append('\n');
                }else{
                    sb.append(1).append('\n');
                }
            }

            else if(x.equals("front")){
                if(!q.isEmpty()){
                    sb.append(q.peek()).append('\n');
                }else{
                    sb.append(-1).append('\n');
                }
            }

            else if(x.equals("back")){
                if(!q.isEmpty()){
                    sb.append(last).append('\n');
                }else{
                    sb.append(-1).append('\n');
                }
            }
        }
        System.out.println(sb);

    }
}
