package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class N과M_10 {

    static int n;
    static int m;
    static List<Integer> list;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        list = new LinkedList<>();
        arr = new int[n];

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

        int tmp = 0;// 중복 수열인지 확인하기 위해 필요한 임시 변수

        for (int i = index; i < n; i++) {
            if (!visited[i] && tmp!= arr[i]) {
                list.add(arr[i]);
                tmp = arr[i];
                visited[i] = true;
                back(i+1);
                list.remove(list.size() - 1);
                visited[i] = false;
            }

        }

    }
}
