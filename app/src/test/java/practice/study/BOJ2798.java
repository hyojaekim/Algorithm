package practice.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2798 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        String[] reqCards = br.readLine().split(" ");
        int[] cards = new int[n];
        for (int i = 0; i < reqCards.length; i++) {
            cards[i] = Integer.parseInt(reqCards[i]);
        }
        int result = solution(n, m, cards);
        System.out.println(result);
    }

    public static int solution(int n, int m, int[] cards) {
        int result = 0;
        cards = sort(cards);
        int sum;
        for (int a = 0; a < n; a++) {
            if (cards[a] >= m) continue;
            for (int b = 0; b < n; b++) {
                if (a == b || cards[a] + cards[b] > m) continue;
                for (int c = 0; c < n; c++) {
                    if (c == a || c == b) continue;
                    sum = cards[a] + cards[b] + cards[c];
                    if (sum <= m) result = Math.max(sum, result);
                }
            }
        }
        return result;
    }

    private static int[] sort(int[] cards) {
        int[] result = new int[cards.length];
        Arrays.sort(cards);
        for (int i = cards.length - 1; i >= 0; i--) {
            result[cards.length - 1 - i] = cards[i];
        }
        return result;
    }
}
