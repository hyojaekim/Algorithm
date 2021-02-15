package practice.boj;

import java.util.Scanner;

public class BOJ1924 {
    private static int[] MAX_DAY = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static String[] WEEK_DAY = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int cntDay = 0;
        for(int i=0; i<x-1; i++){
            cntDay += MAX_DAY[i];
        }
        cntDay += y;
        System.out.println(WEEK_DAY[cntDay%7]);
    }
}
