package 재귀;

import java.io.*;
import java.util.*;

public class 곱셈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // 자연수 a를 b번 곱한 수를 알고 싶다. 이를 c로 나눈 나머지를 구해라

        long result = recur(a, b, c);

        System.out.println(result);
    }

    private static long recur(int a, int b, int c) {
        if(b == 1){
            return a %c;
        }


        long value = recur(a, b/2 ,c);

        if (b %2 == 1){
            return (value * value %c) * a %c;
        }
        return value * value %c;
    }
}
