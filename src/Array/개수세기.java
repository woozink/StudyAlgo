package Array;
import java.io.*;
import java.util.*;

public class 개수세기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int v = Integer.parseInt(br.readLine());

        int count = 0;
        for(int i = 0 ; i < arr.length; i++){
            if(arr[i] == v){
                count ++;
            }
        }

        System.out.println(count);
    }

}
