import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_경로찾기 {
	static int[][] arr;
	static boolean[][] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		arr = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				visit = new boolean[size][size];
				bfs(i, j);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	static void bfs(int i, int j) {
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		boolean flag = false;
		while (!q.isEmpty()) {
			int tmp = q.poll();
			if (flag) {
				if (tmp == j) {
					System.out.print(1);
					return;
				}
			}
			for (int m = 0; m < arr.length; m++) {
				if (arr[tmp][m] == 1 && visit[tmp][m] == false) {
					q.add(m);
					visit[tmp][m] = true;
				}
			}
			flag = true;
		}
		System.out.print(0);
	}
}
