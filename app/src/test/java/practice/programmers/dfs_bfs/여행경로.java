package practice.programmers.dfs_bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 여행경로 {

    boolean[] visited;
    String route;

    public String[] solution(String[][] tickets) {
        this.visited = new boolean[tickets.length];
        this.route = "";

        List<String> result = new ArrayList<>();

        for (int i = 0; i < tickets.length; i++) {
            String startT = tickets[i][0];
            String endT = tickets[i][1];

            if (startT.equals("ICN")) {
                route = startT + ",";
                visited[i] = true;
                dfs(tickets, endT, 1, result);
                visited[i] = false;
            }
        }
        Collections.sort(result);
        return result.get(0).split(",");
    }

    private void dfs(String[][] tickets, String ticket, int cnt, List<String> result) {
        route += ticket + ",";
        if (cnt == tickets.length) {
            result.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            String startT = tickets[i][0];
            String endT = tickets[i][1];

            if (ticket.equals(startT) && !visited[i]) {
                visited[i] = true;
                dfs(tickets, endT, cnt + 1, result);
                visited[i] = false;
                route = route.substring(0, route.length() - 4);
            }
        }
    }
}
