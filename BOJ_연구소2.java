import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_연구소2 {
	static int N, M;
	static int[][] arr, reset;
	static ArrayList<Data> list;
	static Data[] data;
	static int MIN = Integer.MAX_VALUE;
	static int count = 0;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		reset = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					arr[i][j] = -1;
				}
				reset[i][j] = arr[i][j];
			}
		}

		data = new Data[M];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 2) {
					list.add(new Data(i, j, 0));
				}
			}
		}

		dfs(0, 0);
		if (MIN == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(MIN);
	}

	private static void dfs(int start, int depth) {
		if (depth == M) {
			count = 0;
			visit = new boolean[N][N];
			reset();
			Queue<Data> q = new LinkedList<>();
			for (int i = 0; i < data.length; i++) {
				q.add(data[i]);
				visit[data[i].h][data[i].y] = true;
			}
			while (!q.isEmpty()) {
				int size = q.size();
				for (int i = 0; i < size; i++) {
					Data tmp = q.poll();
					for (int k = 0; k < 4; k++) {
						int ah = tmp.h + dh[k];
						int ay = tmp.y + dy[k];
						if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay]
								|| arr[ah][ay] == -1)
							continue;
						visit[ah][ay] = true;
						int num = tmp.cnt + 1;
						arr[ah][ay] = num;
						q.add(new Data(ah, ay, num));
					}

				}
				count++;

			}
			int check = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 0) {
						check++;
					}
				}
			}
			if (check == 0)
				MIN = Math.min(MIN, count - 1);

			return;
		}
		for (int i = start; i < list.size(); i++) {
			data[depth] = list.get(i);
			dfs(i + 1, depth + 1);
		}
	}

	private static void reset() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = reset[i][j];
			}
		}
	}

	static class Data {
		int h;
		int y;
		int cnt;

		public Data(int h, int y, int cnt) {
			super();
			this.h = h;
			this.y = y;
			this.cnt = cnt;
		}

		public Data(int h, int y) {
			super();
			this.h = h;
			this.y = y;
		}

	}

}
