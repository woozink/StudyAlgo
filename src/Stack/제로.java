package Stack;
import java.io.*;
import java.util.*;


public class 제로 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < k;i++){
            int a = Integer.parseInt(br.readLine());

            if(a== 0){
                stack.pop();
            }else{
                stack.push(a);
            }
        }
        int sum = 0;

        for(int i = 0; i < stack.size(); i++){
            sum += stack.get(i);
        }

        System.out.println(sum);
    }
}
