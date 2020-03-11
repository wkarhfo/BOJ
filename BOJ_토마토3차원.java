import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_토마토3차원 {
	static int[] h = { -1, 1, 0, 0, 0, 0 };
	static int[] y = { 0, 0, -1, 1, 0, 0 };
	static int[] he = { 0, 0, 0, 0, 1, -1 };
	static int[][][] arr;
	static Queue<Data> q;
	static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int yeul = sc.nextInt();
		int hang = sc.nextInt();
		int height = sc.nextInt();
		arr = new int[hang][yeul][height];
		result = -1;

		for (int h = 0; h < height; h++) {
			for (int i = 0; i < hang; i++) {
				for (int j = 0; j < yeul; j++) {
					arr[i][j][h] = sc.nextInt();
				}
			}
		}
		q = new LinkedList<>();
		for (int h = 0; h < height; h++) {
			for (int i = 0; i < hang; i++) {
				for (int j = 0; j < yeul; j++) {
					if (arr[i][j][h] == 1) {
						Data data = new Data(i, j, h);
						q.add(data);
					}

				}
			}
		}

		while (!q.isEmpty()) {
			int t = q.size();

			for (int i = 0; i < t; i++) {
				Data d = q.poll();
				for (int j = 0; j < 6; j++) {
					int ah = d.h + h[j];
					int ay = d.y + y[j];
					int aheight = d.height + he[j];
					if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || aheight < 0
							|| aheight >= arr[0][0].length)
						continue;
					if (arr[ah][ay][aheight] == -1 || arr[ah][ay][aheight] == 1)
						continue;

					arr[ah][ay][aheight] = 1;
					Data nd = new Data(ah, ay, aheight);
					q.add(nd);
				}
			}
			result++;
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				for (int h = 0; h < arr[0][0].length; h++) {
					if (arr[i][j][h] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(result);

	}

	static class Data {
		int h;
		int y;
		int height;

		public Data(int h, int y, int height) {
			this.h = h;
			this.y = y;
			this.height = height;
		}

	}
}