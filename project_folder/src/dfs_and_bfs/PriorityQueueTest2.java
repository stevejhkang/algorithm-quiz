package dfs_and_bfs;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PriorityQueueTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String srcString = "1 2 2 1 5 7 2 5 5 5 4 7 2 4 4 4 3 1 4 6 3 2 3 2";
		StringTokenizer stringTokenizer = new StringTokenizer(srcString);
		PriorityQueue<Dot> priorityQueue = new PriorityQueue<>(new Comparator<Dot>() {

			@Override
			public int compare(Dot o1, Dot o2) {
				// TODO Auto-generated method stub
				if (o1.point < o2.point) {
					return 1;
				} else if (o1.point > o2.point) {
					return -1;
				} else {
					if (o1.y > o2.y) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		});
		while (stringTokenizer.hasMoreElements()) {
			int y = Integer.parseInt(stringTokenizer.nextToken());
			int x = Integer.parseInt(stringTokenizer.nextToken());
			int point = Integer.parseInt(stringTokenizer.nextToken());
			System.out.println(y + ":" + x + "," + point);
			Dot aDot = new Dot(y, x, point);
			priorityQueue.add(aDot);
		}
		while (!priorityQueue.isEmpty()) {
			System.out.println(priorityQueue.poll());
		}
//		for (int i = 0; i < priorityQueue.size(); i++) {
//			System.out.println(priorityQueue.poll());
//		}

	}

}

class Dot {
	int y;
	int x;
	int point;

	public Dot(int y, int x, int point) {
		super();
		this.y = y;
		this.x = x;
		this.point = point;
	}

	@Override
	public String toString() {
		return "Dot [y=" + y + ", x=" + x + ", point=" + point + "]";
	}

}
