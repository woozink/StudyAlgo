package Stack활용;

import java.io.*;
import java.util.*;

public class 괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {

            String str = new String(br.readLine());

            if (solve(str)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }

    }

    private static boolean solve(String str) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            if(stack.isEmpty()){
                stack.push(c);
                continue;
            }
            else{
                if(stack.peek() == '('){
                    if(c == ')'){
                        stack.pop();
                    }else{
                        stack.push(c);
                    }
                }

//                else if(stack.peek()== ')'){
//                    if(c == '('){
//                        stack.pop();
//                    }else{
//                        stack.push(c);
//                    }
//                }
            }

        }

        return stack.isEmpty();

    }


}
