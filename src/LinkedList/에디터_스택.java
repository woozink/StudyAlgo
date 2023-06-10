package LinkedList;
import java.io.*;
import java.util.*;

public class 에디터_스택 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String init = br.readLine();

        int n = Integer.parseInt(br.readLine());

        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        for(int i = 0; i < init.length(); i++){
            leftStack.push(init.charAt(i));
        }

        //StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            String[] line = br.readLine().split(" ");

            // 커서 왼쪽으로 한칸 이동
            if(line[0].equals("L")){
                if(!leftStack.isEmpty()){
                    rightStack.push(leftStack.pop());
                }
            }

            // 커서 오른쪽으로 한칸 이동
            else if(line[0].equals("D")){
               if(!rightStack.isEmpty()){
                   leftStack.push(rightStack.pop());
               }
            }

            // 커서 왼쪽 문자 삭제
            else if(line[0].equals("B")){
                if(!leftStack.isEmpty()){
                    leftStack.pop();
                }
            }

            // P 다음 문자 왼쪽 추가
            else if(line[0].equals("P")) {
                leftStack.push(line[1].charAt(0));
            }
        }
        StringBuilder sb = new StringBuilder();

        while(!leftStack.isEmpty()){
            rightStack.push(leftStack.pop());
        }

        while(!rightStack.isEmpty()){
            sb.append(rightStack.pop());
        }

        System.out.println(sb);
    }

}
