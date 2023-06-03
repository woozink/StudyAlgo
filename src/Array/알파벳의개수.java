package Array;
import java.io.*;
import java.util.*;

public class 알파벳의개수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] s1 = s.split("");

        int[] arr = new int[26];

        for(int i = 0; i < s1.length; i++){
            if(s1[i].equals("a")){
                arr[0]++;
            }else if(s1[i].equals("b")){
                arr[1]++;
            }else if(s1[i].equals("c")){
                arr[2]++;
            }else if(s1[i].equals("d")){
                arr[3]++;
            }else if(s1[i].equals("e")){
                arr[4]++;
            }else if(s1[i].equals("f")){
                arr[5]++;
            }else if(s1[i].equals("g")){
                arr[6]++;
            }else if(s1[i].equals("h")){
                arr[7]++;
            }else if(s1[i].equals("i")){
                arr[8]++;
            }else if(s1[i].equals("j")){
                arr[9]++;
            }else if(s1[i].equals("k")){
                arr[10]++;
            }else if(s1[i].equals("l")){
                arr[11]++;
            }else if(s1[i].equals("m")){
                arr[12]++;
            }else if(s1[i].equals("n")){
                arr[13]++;
            }else if(s1[i].equals("o")){
                arr[14]++;
            }else if(s1[i].equals("p")){
                arr[15]++;
            }else if(s1[i].equals("q")){
                arr[16]++;
            }else if(s1[i].equals("r")){
                arr[17]++;
            }else if(s1[i].equals("s")){
                arr[18]++;
            }else if(s1[i].equals("t")){
                arr[19]++;
            }else if(s1[i].equals("u")){
                arr[20]++;
            }else if(s1[i].equals("v")){
                arr[21]++;
            }else if(s1[i].equals("w")){
                arr[22]++;
            }else if(s1[i].equals("x")){
                arr[23]++;
            }else if(s1[i].equals("y")){
                arr[24]++;
            }else if(s1[i].equals("z")){
                arr[25]++;
            }
        }

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+ " ");
        }


    }
}
