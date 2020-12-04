package programmers.dfs_bfs;

import java.util.*;

public class 여행경로2 {

    /**
     * 모두 소진, 3글자
     * 여러 개가 존재하면 알파벳이 앞서는 것을 택한다.
     * @param tickets 티켓 정보 a -> b
     * @return answer 여행경로
     */
    boolean[] visited;
    String[][] tickets;
    List<String> routes;
    public String[] solution(String[][] tickets) {
        this.visited = new boolean[tickets.length];
        this.tickets = tickets;
        this.routes = new ArrayList<>();
        findTravelRoute("ICN", new LinkedList<>());
        Collections.sort(routes);
        return routes.get(0).split(",");
    }

    private void findTravelRoute(String current, Queue<Integer> q) {
        if (q.size() == tickets.length) {
            //경로 저장하기
            StringBuilder route = new StringBuilder("ICN");
            for (Integer ticketNumber : q) {
                route.append(",").append(tickets[ticketNumber][1]);
            }
            routes.add(route.toString());
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) continue;
            if (!current.equals(tickets[i][0])) continue;
            q.add(i);
            visited[i] = true;
            findTravelRoute(tickets[i][1], q);
            visited[i] = false;
            q.remove(i);
        }
    }
}
