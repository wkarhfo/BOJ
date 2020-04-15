import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_치킨배달 {
	static int N, M;
	static int[][] arr;
	static ArrayList<Data> list;
	static boolean[] visit;
	static int[][] count;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit2;
	static int MIN=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					list.add(new Data(i, j));
				}
			}
		}
		visit = new boolean[list.size()];
		dfs(0, 0);
		System.out.println(MIN);

	}

	static void dfs(int start, int depth) {
		if (depth == M) {
			count = new int[N][N];
			for (int i = 0; i < visit.length; i++) {
				if (visit[i]) {
					visit2 = new boolean[N][N];
					bfs(list.get(i));
				}
			}
			int result = 0;
			for (int i = 0; i < count.length; i++) {
				for (int j = 0; j < count.length; j++) {
					if (count[i][j] > 0)
						result += count[i][j];
//					System.out.print(count[i][j]+" ");
				}
//				System.out.println();
			}
			MIN=Math.min(MIN, result);
//			System.out.println(result);
			return;
		}
		for (int i = start; i < list.size(); i++) {
			visit[i] = true;
			dfs(i + 1, depth + 1);
			visit[i] = false;
		}
	}

	static void bfs(Data data) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(data.h, data.y, 0));
		visit2[data.h][data.y] = true;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			if (arr[tmp.h][tmp.y] == 1) {
				if (count[tmp.h][tmp.y] == 0) {
					count[tmp.h][tmp.y] = tmp.cnt;
				} else if (count[tmp.h][tmp.y] > tmp.cnt) {
					count[tmp.h][tmp.y] = tmp.cnt;
				} else {
					continue;
				}
			}
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit2[ah][ay] )
					continue;
				visit2[ah][ay] = true;
				q.add(new Data(ah, ay, tmp.cnt + 1));

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
