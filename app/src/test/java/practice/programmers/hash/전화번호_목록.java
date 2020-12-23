package practice.programmers.hash;

import java.util.Arrays;
import java.util.Comparator;

public class 전화번호_목록 {

    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, Comparator.comparingInt(String::length));
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = i + 1; j < phone_book.length; j++) {
                if (phone_book[j].startsWith(phone_book[i])) return false;
            }
        }
        return true;
    }
}
