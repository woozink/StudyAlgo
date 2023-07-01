package 유튜브;
import java.io.*;
import java.util.*;

public class 도미노_14586 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int value;

        value = recur(n, 0);
        System.out.println(value);
    }

    private static int recur(int n, int count) {
        if (n==0){
            return count;
        }

        return (recur(n-1, count+n));

    }
}




