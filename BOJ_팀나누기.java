import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_팀나누기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int people = sc.nextInt();
		int[][] arr = new int[people + 1][people + 1];
		boolean[][] visit = new boolean[people + 1][people + 1];
		int[] result = new int[people + 1];
		for (int i = 1; i <= people; i++) {
			int a = sc.nextInt();
			for (int j = 0; j < a; j++) {
				int b = sc.nextInt();
				arr[i][b] = arr[b][i] = 1;
			}
		}

		for (int i = 1; i <= people; i++) {
			Queue<Integer> q = new LinkedList<>();
			if (result[i] == 0) {
				result[i] = 1;
				q.add(i);
				while (!q.isEmpty()) {
					int tmp = q.poll();
					for (int j = 1; j < arr.length; j++) {
						if (arr[j][tmp] == 1 && visit[j][tmp] == false) {
							if (result[j] == 0) {
								result[j] = result[tmp] * -1;
								visit[j][tmp] = true;
								visit[tmp][j] = true;
								q.add(j);
							}
						}
					}
				}

			}
		}
		ArrayList<Integer> A = new ArrayList<>();
		Collections.sort(A);
		ArrayList<Integer> B = new ArrayList<>();
		Collections.sort(B);
		for (int i = 1; i < result.length; i++) {
			if (result[i] == 1) {
				A.add(i);
			} else {
				B.add(i);
			}
		}
		System.out.println(A.size());
		for (int i = 0; i < A.size(); i++)
			System.out.print(A.get(i)+" ");
		System.out.println();
		System.out.println(B.size());
		for (int i = 0; i < B.size(); i++)
			System.out.print(B.get(i)+" ");
	}

}
