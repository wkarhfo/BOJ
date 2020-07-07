import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_아기상어2 {
	static int N, M;
	static int[][] arr;
	static boolean[][] visit;
//	static int[][] distance;
	static int[] dh = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int length;
	static int MAX;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
//		distance = new int[N][M];
		MAX = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// ======= 입력 끝=================
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					length = 0;
					visit = new boolean[N][M];
					bfs(i, j);
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				if (MAX < distance[i][j]) {
//					MAX = distance[i][j];
//				}
//			}
//		}
		System.out.println(MAX);
	}

	static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		visit[h][y] = true;
		q.add(new Data(h, y, 0));
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 8; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
					continue;
				if (arr[ah][ay] == 1) {
//					distance[h][y] = tmp.len + 1;
					if (MAX < tmp.len + 1) {
						MAX = tmp.len + 1;
					}
					return;
				}
				q.add(new Data(ah, ay, tmp.len + 1));
				visit[ah][ay] = true;
			}
		}
	}

	static class Data {
		int h;
		int y;
		int len;

		public Data(int h, int y, int len) {
			super();
			this.h = h;
			this.y = y;
			this.len = len;
		}

	}
}
