package LinkedList;
import java.io.*;
import java.util.*;

public class 에디터 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String init = br.readLine();

        int n = Integer.parseInt(br.readLine());

        List<Character> list = new LinkedList<>();

        for(int i = 0; i < init.length(); i++){
            list.add(init.charAt(i));
        }


        int size = list.size();
        int index = list.size();


        //StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            String[] line = br.readLine().split(" ");

            // 커서 왼쪽으로 한칸 이동
            if(line[0].equals("L")){
                if(index > 0){
                    index--;
                }
            }

            // 커서 오른쪽으로 한칸 이동
            else if(line[0].equals("D")){
                if(index <size){
                    index++;
                }
            }

            // 커서 왼쪽 문자 삭제
            else if(line[0].equals("B")){
                if (index > 0) {
                    list.remove(index -1);
                    index--;
                }
            }

            // P 다음 문자 왼쪽 추가
            else if(line[0].equals("P")){
                list.add(index, line[1].charAt(0));
                index++;
            }
        }
        StringBuilder sb = new StringBuilder();

        for(char c : list){
            sb.append(c);
        }
        System.out.println(sb);

    }

}
