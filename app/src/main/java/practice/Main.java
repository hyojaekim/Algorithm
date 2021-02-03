package practice;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[][] info = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                info[i][j] = Integer.parseInt(line[j]);
            }
        }

        Main main = new Main();
        main.solution(n, info);
    }

    int INF = 100000;
    public void solution(int n, int[][] info) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (info[i][j] == 0) info[i][j] = INF;
            }
        }

        floyd(n, info);

        for (int[] a : info) {
            for (int i = 0; i < n; i++) {
                if (a[i] == INF) System.out.print(0 + " ");
                else System.out.print(a[i] + " ");
            }
            System.out.println();
        }
    }

    private void floyd(int n,int[][] info) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (info[i][k] + info[k][j] < info[i][j]) info[i][j] = 1;
                }
            }
        }
    }
}