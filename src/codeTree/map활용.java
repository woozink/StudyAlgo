package codeTree;

import java.util.*;

public class map활용 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Map<Integer, List<Integer>> pigeonMap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int pigeonNum = sc.nextInt();
            int position = sc.nextInt();

            pigeonMap.putIfAbsent(pigeonNum, new ArrayList<>());
            pigeonMap.get(pigeonNum).add(position);
        }

        int totalCrosses = 0;

        for(List<Integer> positions : pigeonMap.values()) {
            totalCrosses += check(positions);
        }

        System.out.println(totalCrosses);
    }

    public static int check(List<Integer> list) {
        if(list.size() < 2) {
            return 0;
        }

        int count = 0;
        for(int i = 1; i < list.size(); i++) {
            if(!list.get(i).equals(list.get(i-1))) {
                count++;
            }
        }
        return count;
    }
}
