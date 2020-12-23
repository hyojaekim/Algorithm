package practice.boj.iftype;

import java.io.*;

public class BOJ9498 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int userNum = Integer.valueOf(br.readLine());
        userNum = userNum / 10;
        char result;
        switch (userNum){
            case 10:
            case 9 :
                result = 'A';
                break;
            case 8 :
                result = 'B';
                break;
            case 7 :
                result = 'C';
                break;
            case 6 :
                result = 'D';
                break;
            default :
                result = 'F';
                break;
        }
        bw.write(result);
        br.close();
        bw.close();
    }
}
