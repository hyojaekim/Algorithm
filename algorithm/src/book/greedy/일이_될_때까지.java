package book.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 일이_될_때까지 {

    public int solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int cnt = 0;
        while (n != 1) {
            n = (n % k == 0) ? n / k : n - 1;
            cnt++;
        }
        return cnt;
    }
}
