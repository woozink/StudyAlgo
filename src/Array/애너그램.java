package Array;
import java.io.*;
import java.util.*;

public class 애너그램 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        // 알파벳의 개수
        int [] arr1 = new int[26];
        int [] arr2 = new int[26];

        // 배열에 맵핑 1
        for(char ch : str1.toCharArray()){
            arr1[ch -'a'] ++;
        }

        // 배열에 맵핑 2
        for(char ch : str2.toCharArray()){
            arr2[ch -'a'] ++;
        }

        int removalCount = 0;

        for(int i = 0; i < 26; i++){
            removalCount += Math.abs(arr1[i] - arr2[i]);
        }

        System.out.println(removalCount);




    }
}
