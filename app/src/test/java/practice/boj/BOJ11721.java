package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11721 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str_number = br.readLine();
        for(int i=0; i<str_number.length(); i++){
            System.out.print(str_number.charAt(i));
            if((i+1)%10==0){
                System.out.println();
            }
        }
    }
}
