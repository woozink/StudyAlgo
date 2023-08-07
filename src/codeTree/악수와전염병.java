package codeTree;

import java.util.*;
class Node{
    int t;
    int x;
    int y;
    public Node(int t, int x, int y){
        this.t=t;
        this.x = x;
        this.y =y;
    }
}
public class 악수와전염병 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 사람수
        int K = sc.nextInt(); // 1인당 악수
        int P = sc.nextInt(); // 걸린놈
        int T = sc.nextInt(); // 총 횟수


        int[] member = new int[N+1];
        Node[] arr= new Node[T];
        int[] count = new int[N+1];

        member[P] = 1;

        for(int i = 0; i < T; i++){
            int t = sc.nextInt();
            int x= sc.nextInt();
            int y = sc.nextInt();

            arr[i] = new Node(t, x, y);
        }

        Arrays.sort(arr, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                return o1.t - o2.t;
            }
        });


        for(int i = 0; i < T; i++){
            int x = arr[i].x;
            int y = arr[i].y;
            if(member[x] == 1 || member[y] == 1){
                if(count[x] >= K || count[y] >= K){
                    continue;
                }else{
                    member[x] = 1;
                    member[y] = 1;
                    count[x]++;
                    count[y]++;
                }

            }
        }

        for(int i = 1; i <=N; i++){
            System.out.print(member[i]);
        }
    }
}