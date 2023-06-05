package Array;
import java.io.*;
import java.util.*;

public class Strfry {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split(" ");
            String a = input[0];
            String b = input[1];

            if(isStr(a,b)){
                System.out.println("Possible");
            }else{
                System.out.println("Impossible");
            }
        }
    }

    private static boolean isStr(String a, String b) {
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();

        Arrays.sort(charA);
        Arrays.sort(charB);

        a = new String(charA);
        b = new String(charB);

        return a.equals(b);
    }
}
