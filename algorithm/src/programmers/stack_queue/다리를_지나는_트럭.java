package programmers.stack_queue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

//offer 넣기
//peek 삭제하지 않고 뺌
//poll 꺼냄
//isEmpty 비었는지 확인함
public class 다리를_지나는_트럭 {

    class Truck {
        int weight;
        int position;

        public Truck(int weight) {
            this.weight = weight;
            this.position = 0;
        }

        public void move() {
            this.position++;
        }
    }

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        Queue<Truck> waitTrucks = new LinkedList<>();
        Queue<Truck> moveTrucks = new LinkedList<>();

        int time = 1;
        int bridgeWeight = 0;

        for (int i = 0; i < truckWeights.length; i++) {
            waitTrucks.offer(new Truck(truckWeights[i]));
        }

        while (!moveTrucks.isEmpty() || !waitTrucks.isEmpty()) {
            if (!waitTrucks.isEmpty() && waitTrucks.peek().weight + bridgeWeight <= weight) {
                Truck truck = waitTrucks.poll();
                bridgeWeight += truck.weight;
                moveTrucks.offer(truck);
            }

            Iterator<Truck> iterator = moveTrucks.iterator();
            while (iterator.hasNext()) {
                Truck truck = iterator.next();
                truck.move();
                if (truck.position == bridgeLength) {
                    bridgeWeight -= truck.weight;
                    iterator.remove();
                }
            }
            time++;
        }
        return time;
    }
}