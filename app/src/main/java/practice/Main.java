package practice;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] firstLine = sc.nextLine().split(" ");
        int vCnt = Integer.parseInt(firstLine[0]);
        int eCnt = Integer.parseInt(firstLine[1]);
        int start = Integer.parseInt(sc.nextLine());

        int[][] info = new int[eCnt][3];
        for (int i = 0; i < eCnt; i++) {
            String[] line = sc.nextLine().split(" ");
            info[i][0] = Integer.parseInt(line[0]);
            info[i][1] = Integer.parseInt(line[1]);
            info[i][2] = Integer.parseInt(line[2]);
        }
        Main main = new Main();
        main.solution(vCnt, eCnt, info, start);
    }

    public void solution(int vCnt, int eCnt, int[][] info, int start) {
        Map<Integer, List<Node>> graph = init(info, vCnt); //그래프 초기화(정점, 간선, 가중치)
        Map<Integer, Integer> dist = new HashMap<>(); //노드의 최소거리를 관리한다.

        dijkstra(graph, dist, start);
    }

    private Map<Integer, List<Node>> init(int[][] info, int vCnt) {
        Map<Integer, List<Node>> result = new HashMap<>();
        for (int i = 1; i <= vCnt; i++) {
            result.put(i, new ArrayList<>());
        }
        for (int[] a : info) {
            result.get(a[0]).add(new Node(a[1], a[2]));
        }
        return result;
    }

    private void dijkstra(Map<Integer, List<Node>> graph, Map<Integer, Integer> dist, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w)); //우선순위 큐 생성
        pq.offer(new Node(start, 0)); //시작 노드 추가

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (!dist.containsKey(cur.v)) {
                dist.put(cur.v, cur.w);
                for (Node node : graph.get(cur.v)) {
                    pq.offer(new Node(node.v, node.w + cur.w));
                }
            }
        }

        for (int i = 1; i <= graph.size(); i++) {
            if (!dist.containsKey(i)) System.out.println("INF");
            else System.out.println(dist.get(i));
        }
    }

    class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}