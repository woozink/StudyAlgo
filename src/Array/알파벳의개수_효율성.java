package Array;
import java.io.*;
import java.util.*;

public class 알파벳의개수_효율성 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] arr = new int[26];


        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            arr[c-97]++;
        }

        for(int i = 0; i < 26; i++){
            System.out.print(arr[i]+ " ");
        }
    }
}

