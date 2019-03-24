import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int result = -1, five = (num/5) * 5;

        if(num == 4 || num < 3){
        }else if(num % 5 == 0){
            result = num / 5;
        }else{
            while(true){
                int n = num - five;
                if(five == 0){
                    if(num%3 == 0){
                        result = num/3;
                    }
                    break;
                }
                if(n % 3 == 0){
                    result = (five/5) + (n/3);
                    break;
                }
                five = five - 5;
            }
        }

        System.out.print(result);
    }
}
