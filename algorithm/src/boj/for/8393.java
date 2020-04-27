
import java.util.Scanner;

public class Main {
    private static int[] MAX_DAY = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static String[] WEEK_DAY = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int maxNum = sc.nextInt();
        int result = 0;
        for(int i=1; i<=maxNum; i++){
            result += i;
        }
        System.out.println(result);
    }
}
