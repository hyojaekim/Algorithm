package practice.boj.fortype;

import java.io.*;

public class BOJ15552 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.valueOf(br.readLine());
        for(int i=0; i<T; i++){
            String line = br.readLine();
            String[] str_num = line.split(" ");
            int a = Integer.valueOf(str_num[0]);
            int b = Integer.valueOf(str_num[1]);
            bw.write((a+b) + "\n");
        }
        bw.close();
        br.close();
    }
}
