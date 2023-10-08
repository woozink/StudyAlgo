package codeTree;

import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        int r;
        int c;
        int moveCount;

        public Node(int r, int c, int moveCount){
            this.r =r;
            this.c =c;
            this.moveCount = moveCount;
        }
    }

    static int n, m, k;
    static int[][] map;
    static int finalX, finalY;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<Node> list = new ArrayList<>();

    public static void main(String[] args) {
        // 입력
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 미로의 크기
        m = sc.nextInt(); // 참가자의 수
        k = sc.nextInt(); // 게임 시간
        list = new ArrayList<>();
        map = new int[n+1][n+1];
        Node node = new Node(0,0,0);

        for(int i = 1; i<= n; i++){
            for(int j = 1; j <= n; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < m; i++){
            list.add(new Node(sc.nextInt(), sc.nextInt(), 0));
        }

        finalX = sc.nextInt();
        finalY = sc.nextInt();

        // 풀이 시작

        // k번 반복
        for(int i = 0; i < k; i++){
            // k 1번에 각각 인원 수 만큼 반복
            for(int j = 0; j < m; j++){
                Node currentNode = list.get(j);
                move(currentNode.r, currentNode.c, j);
            }
        }

        // 출력

        int totalMoveCount = 0;
        for(int i = 0; i < m; i++){
            totalMoveCount += list.get(i).moveCount;
        }
        System.out.println(totalMoveCount);
        System.out.print(finalX +" " + finalY);
    }
    // map 에서 상하좌우 순서로 체크하면서 0이 있는지를 확인한다.
    // 만약 0이라면 checkMan 이 true이면
    // nextR, nextC를 savePosition 하고 턴을 종료.아니면 원래 위치의 savePosition
    // list 값 초기화 list.countMem()
    // 움직였다면 moveCount ++;
    // min manhatan = savePosition(r, c);
    // 만약 min manhatan이 갱신이 되면 int minR, minC에 현재 좌표값 넣어주기.
    // makeSquare(minR, minC);
    public static void move(int r, int c, int count){
        for(int i = 0; i < 4; i++){
            int nx = r + dx[i];
            int ny = c + dy[i];

            if(map[nx][ny] !=0 && nx<1 &&nx >=n+1 && ny<1 && ny >= n+1){
                continue;
            }
            int minManhatan = Integer.MAX_VALUE;
            int saveValue = 0;
            int minR =0;
            int minC =0;

            if(checkMan(r,c,nx,ny)){
                saveValue = savePosition(nx,ny);
                list.get(count).r = nx;
                list.get(count).c = ny;
                list.get(count).moveCount +=1;
                if(minManhatan > saveValue){
                    minR = nx;
                    minC = ny;
                }
            }else{
                saveValue = savePosition(r, c);
                if(minManhatan > saveValue){
                    minR = r;
                    minC = c;
                }
            }

            makeSquare(minR, minC);
            break;
        }
    }

    // 절대 값 연산을 이용해서, finalX - r / finalY -c
    // finalX - nextr / finalY - nextC 의 값을 비교한다.
    // a > b 이면 return true;
    // 아니면 return false
    public static boolean checkMan(int r, int c, int nextR, int nextC){
        int a = Math.abs(finalX - r)+ Math.abs(finalY- c);
        int b = Math.abs(finalX - nextR) + Math.abs(finalY - nextC);

        if(a > b){
            return true;
        }else {
            return false;
        }
    }
    // 멘하탄 값을 구해서 반환
    public static int savePosition(int r, int c){
        int a = Math.abs(finalX - r)+ Math.abs(finalY- c);

        return a;
    }
    // a = finalX - r 이 음수인지 양수인지 판단.
    // b = finalY -c 가 음수인지 양수인지 판단.
    // a, b 가 음수일 때
    // a, b가 양수일때 (크거나 같을 때)
    // a는 양수이고, b는 음수일때
    // b는 음수이고, a는 양수일때
    public static void makeSquare(int r, int c){
        int a = finalX -r;
        int b = finalY -c;

        if(a <0 && b < 0){
            rotateA(r,c);
        }else if(a >= 0 && b>=0){
            rotateB(r,c);
        }else if(a >=0 && b< 0){
            rotateC(r,c);
        }else if(a<0 && b >= 0){
            rotateD(r,c);
        }
    }
    // 범위 지정
    // 90 도 돌리고
    // 값 -1
    // final X , final Y 갱신
    // arr 갱신
    // a, b 가 음수일 때
    public static void rotateA(int r, int c){
    }
    // a, b가 양수일때 (크거나 같을 때)
    public static void rotateB(int r, int c){

    }
    // a는 양수이고, b는 음수일때
    public static void rotateC(int r, int c){

    }
    // b는 음수이고, a는 양수일때
    public static void rotateD(int r, int c){

    }



}
