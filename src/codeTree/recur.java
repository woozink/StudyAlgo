import java.util.*;

public class recur {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int a = recur(n);
        System.out.println(a);
    }

    public static int recur(int n) {

        // 기저 조건 ->
        if(n <= 0){
            if(n%2 ==0){
                return 2;
            }
        }


        if((n-1) % 2 ==0){
            return recur(n-1) + n;
        }else{
            return recur(n-1);
        }
    }
}