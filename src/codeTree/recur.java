package codeTree;

import java.util.*;

public class recur {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = recur(n);

        System.out.print(sum);
    }

    public static int recur(int n){

        if(n < 10){
            return n*n;
        }

        return recur(n/10) + recur(n%10);

    }
}