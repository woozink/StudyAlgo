package 백트래킹;
import java.io.*;
import java.util.*;


public class 부분수열의합 {
    static int n ,s;
    static int[] arr;
    static int count;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i]= Integer.parseInt(st.nextToken());
        }
        
        back(0,0);
        if(s == 0) count --; // 공집합은 빼줘야함
        System.out.println(count);

    }

    private static void back(int cur, int sum) {
        // 기저 조건
        if(cur== n){
            if(sum == s)count ++;
            return;
        }
        // 안넣고
        back(cur +1, sum);
        // 넣고
        back(cur +1, sum +arr[cur]);
    }
}
