package Array;
import java.io.*;
import java.util.*;

public class 방번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();

        int[] count = new int[10];

        for(int i = 0; i < n.length(); i++){
            char c = n.charAt(i);
            int a = c -'0';
            count[a] ++;
        }

        int max = Integer.MIN_VALUE;
        int k = (count[6] + count[9]);

        if(k %2 ==0){
            count[6] = k/2;
            count[9] = k/2;
        }

        else{
            count[6] = k/2+1;
            count[9] = k/2+1;
        }

        for(int i = 0; i < count.length; i++){
            if(count[i] -1 >=max){
                max = count[i];
            }
        }

        System.out.println(max);


    }

}
