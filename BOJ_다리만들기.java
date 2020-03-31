import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_다리만들기 {
	static int N;
	static int[][] arr;
	static int[][] count;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int MIN = Integer.MAX_VALUE;
	static int num = 1;
	static int time = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N][N];
		count = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1 && !visit[i][j]) {
					bfs(i, j);
					num++;
				}
			}
		}

		for (int m = 0; m < N; m++) {
			for (int n = 0; n < N; n++) {
				count[m][n] = arr[m][n];
			}
		}
		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 0) {
					bfs2(i, j);
					reset();
				}
			}
		}
		System.out.println(MIN);
	}

	private static void reset() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = count[i][j];
			}
		}
	}

	private static void bfs2(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(h, y, 0));
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length)
					continue;

				if (arr[ah][ay] == arr[h][y]) {
					continue;
				}
				if (arr[ah][ay] == -1) {
					continue;
				}

				if (arr[ah][ay] != 0) {
					MIN = Math.min(MIN, tmp.no);
					return;
				}
				arr[ah][ay] = -1;
				q.add(new Data(ah, ay, tmp.no + 1));

			}
		}
	}


	private static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(h, y));
		visit[h][y] = true;
		arr[h][y] = num;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay] || arr[ah][ay] == 0)
					continue;

				arr[ah][ay] = num;
				q.add(new Data(ah, ay));
				visit[ah][ay] = true;

			}
		}
	}

	static class Data {
		int h;
		int y;
		int no;

		public Data(int h, int y, int no) {
			super();
			this.h = h;
			this.y = y;
			this.no = no;
		}

		public Data(int h, int y) {
			super();
			this.h = h;
			this.y = y;
		}

	}

}
