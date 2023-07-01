package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class N과M_6 {

    static int n;
    static int m;
    static int[] arr;
    static List<Integer> list;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        visited = new boolean[n];
        list = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

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
                visited[i] = true;
                list.add(arr[i]);
                back(i + 1);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }

    }
}
