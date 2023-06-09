package Stack;

import java.io.*;
import java.util.*;


public class 옥상정원꾸미기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int count = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            while (!stack.isEmpty()) {

                if (stack.peek() <= x) {
                    stack.pop();
                } else {
                    break;
                }


            }

            count += stack.size();

            stack.push(x);

        }
        System.out.println(count);

    }

}
