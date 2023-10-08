package codeTree;
import java.util.*;

public class test {

    static int n, m;
    static int[][] arr;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr= new int[n][n];

        for(int i = 0; i < n ; i ++){
            for(int j = 0 ; j < n; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int sum = 0;
        for(int i = 0 ; i < n; i++){
            if(rowCheck(i)){
                sum+=1;
            }

            if(colCheck(i)){
                sum+=1;
            }
        }
        System.out.println(sum);
    }

    public static boolean rowCheck(int row){


        for(int i = 0; i < n; i++){
            int count = 1;

            for(int j = i+1; j < n; j++){

                if(arr[row][i] == arr[row][j]){
                    count++;
                }
                if(count == m){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean colCheck(int col){


        for(int i = 0; i < n; i++){
            int count = 1;

            for(int j = i+1; j < n; j++){

                if(arr[i][col] == arr[j][col]){
                    count++;
                }
                if(count == m){
                    return true;
                }
            }
        }

        return false;
    }
}