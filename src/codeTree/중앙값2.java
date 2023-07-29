package codeTree;

import java.util.*;

public class 중앙값2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List list = new LinkedList<>();

        int[] arr = new int[n];

        for(int i = 0; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        int x =1;

        for(int i = 0; i < n; i++){
            list.add(arr[i]);
            if(i % 2 == 0){
                if(i==0){
                    System.out.print(arr[0]+" ");
                }else{
                    Collections.sort(list);

                    System.out.print(list.get(x)+" ");
                    x+=1;
                }
            }
        }

    }
}