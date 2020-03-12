import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Cheeze {
	static int[] dh = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit;
	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int hang = sc.nextInt();
		int yeul = sc.nextInt();
		arr = new int[hang][yeul];
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < yeul; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int hour = 0;
		ArrayList<Data> list;
		ArrayList<Integer> result = new ArrayList<>();
		while (true) {
			list = new ArrayList<>();
			visit = new boolean[hang][yeul];
			bfs(0, 0);

			int count = 0;
			for (int i = 1; i < hang - 1; i++) {
				for (int j = 1; j < yeul - 1; j++) {
					if (arr[i][j] == 1) {
						count++;
					}
				}
			}
			for (int i = 1; i < hang - 1; i++) {
				for (int j = 1; j < yeul - 1; j++) {
					if (arr[i][j] == 1) {
						for (int k = 0; k < 4; k++) {
							int ah = i + dh[k];
							int ay = j + dy[k];
							if (arr[ah][ay] == 2) {
								Data temp = new Data(i, j);
								list.add(temp);
								break;
							}
						}
					}
				}
			}
			for (int i = 0; i < list.size(); i++) {
				arr[list.get(i).h][list.get(i).y] = 2;
			}
			
			result.add(count);
			if (count == 0) {
				System.out.println(hour);
				break;
			}
			hour++;
		}
		for (int i = 0; i < result.size(); i++)
			if(i==hour-1)
				System.out.print(result.get(i)+" ");

	}

	static void bfs(int i, int j) {
		Queue<Data> q = new LinkedList<>();
		Data start = new Data(i, j);
		q.add(start);
		visit[i][j] = true;
		arr[i][j] = 2;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay] == true
						|| arr[ah][ay] == 1) {
					continue;
				}
				visit[ah][ay] = true;
				arr[ah][ay] = 2;
				Data newData = new Data(ah, ay);
				q.add(newData);
			}
		}

	}

	static class Data {
		int h;
		int y;

		public Data(int h, int y) {
			this.h = h;
			this.y = y;
		}
	}
}
