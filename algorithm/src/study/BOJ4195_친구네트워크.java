package study;

import java.util.HashMap;
import java.util.Map;

public class BOJ4195_친구네트워크 {



    Map<String, Integer> friendIndex;
    int[] parent;
    public void solution() {
        this.friendIndex = new HashMap<>();

        solution(3, new String[]{
                "Fred Barney",
                "Barney Betty",
                "Betty Wilma"
        });
    }

    public void solution(int m, String[] friendNetwork) {
        int nowIndex = 0;
        this.parent = new int[m * 2];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (String info : friendNetwork) {
            String[] friends = info.split(" ");
            String firstFriend = friends[0];
            String secondFriend = friends[1];

            if (!this.friendIndex.containsKey(firstFriend)) {
                this.friendIndex.put(firstFriend, nowIndex++);
            }

            if (!this.friendIndex.containsKey(secondFriend)) {
                this.friendIndex.put(secondFriend, nowIndex++);
            }

            int result = merge(friendIndex.get(firstFriend), friendIndex.get(firstFriend));
            System.out.println(result);
        }
    }

    private int merge(int aIndex, int bIndex) {
        int result = 0;
        int a = getParent(aIndex);
        int b = getParent(bIndex);

        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        this.parent[b] = a;
        for (int p : this.parent) {
            result += p;
        }
        return result;
    }

    private int getParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }
}
