import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_빙산 {
	static int[][] arr;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int iceCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int hang = Integer.parseInt(st.nextToken());
		int yeul = Integer.parseInt(st.nextToken());
		arr = new int[hang][yeul];

		for (int i = 0; i < hang; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < yeul; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int year=0;
		while (true) {
			int count = 0;
			year++;
			for (int i = 0; i < hang; i++) {
				for (int j = 0; j < yeul; j++) {
					if (arr[i][j] > 0)
						count++;
				}
			}
			if (count == 0) {
				System.out.println(0);
				return;
			}
			ArrayList<Data> list = new ArrayList<>();
			for (int i = 0; i < hang; i++) {
				for (int j = 0; j < yeul; j++) {
					if (arr[i][j] > 0) {
						int count1 = 0;
						for (int k = 0; k < 4; k++) {
							int ah = i + dh[k];
							int ay = j + dy[k];
							if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length)
								continue;
							if (arr[ah][ay] == 0)
								count1++;
						}
						list.add(new Data(i, j, count1));
					}
				}
			}

			for (int i = 0; i < list.size(); i++) {
				for (int j = 1; j <= list.get(i).wnum; j++) {
					if (arr[list.get(i).h][list.get(i).y] == 0)
						continue;
					arr[list.get(i).h][list.get(i).y]--;
				}
			}
			iceCount = 0;
			visit = new boolean[hang][yeul];

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					if (arr[i][j] > 0 && visit[i][j] == false) {
						bfs(i, j);
						iceCount++;
					}
				}
			}
			if(iceCount>1) {
				System.out.println(year);
				return;
			}

		}
	}

	private static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		visit[h][y] = true;
		q.add(new Data(h, y));
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay] == true
						|| arr[ah][ay] == 0)
					continue;
				visit[ah][ay] = true;
				q.add(new Data(ah, ay));
			}
		}

	}

	static class Data {
		int h;
		int y;
		int wnum;

		public Data(int h, int y) {
			super();
			this.h = h;
			this.y = y;
		}

		public Data(int h, int y, int wnum) {
			super();
			this.h = h;
			this.y = y;
			this.wnum = wnum;
		}

	}

}
