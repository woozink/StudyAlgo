package Array;

import java.io.*;
import java.util.*;

public class 숫자의개수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        int x = a*b*c;
        int[] arr = new int[10];

        while(x !=0){
            int y = x % 10;
            x = x/10;
            arr[y]++;
        }


        for(int i = 0; i< arr.length; i++){
            System.out.println(arr[i]);
        }
    }

}
