package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N과M_8 {

    static int n;
    static int m;
    static int[] arr;
    static List<Integer> list;
    static List<Integer> list2;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        list = new LinkedList<>();
        list2 = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list2.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list2);
        for(int i = 0; i < list2.size(); i++){
            if(i != 0 ){
                if(list2.get(i) == list2.get(i-1)){
                    list2.remove(i);
                }
            }
        }

        n = list2.size();

        back(0);
        System.out.println(sb);
    }

    private static void back(int index) {

        // m개를 골랐다면,
        if (list.size() == m) {
            for (int num : list) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = index; i < n; i++) {
            if (!visited[i]) {
                list.add(list2.get(i));
                visited[i] = true;
                back(i);
                list.remove(list.size() - 1);
                visited[i] = false;
            }

        }

    }
}
