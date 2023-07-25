package codeTree;
import java.util.*;

public class 문자삭제 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();

        int aLen = a.length();
        int bLen= b.length();

        while(true){

            int k = 0;
            boolean flag = false;

            // 찾기
            for(int i = 0; i < aLen- bLen; i++){
                int count = 0;
                for(int j = 0; j < bLen; j++){
                    if(a.charAt(i+j)== b.charAt(j)){
                        count++;
                    }
                }
                if(count == bLen){
                    k = i;
                    flag = true;
                    break;
                }
            }

            if(!flag) break;


            // 삭제
            a= a.substring(0,k) + a.substring(k+bLen, aLen);
            aLen = a.length();



        }

        System.out.print(a);



    }
}