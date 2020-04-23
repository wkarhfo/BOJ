import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_인구이동 {
	static int N, L, R;
	static int[][] arr;
	static boolean[][] visit;
	static int[][] map;
	static boolean flag;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int count = 0;
	static int no;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			map = new int[N][N];
			flag = false;
			no = 51;
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						bfs(i, j);
					}
				}
			}
		
			if (flag)
				sum(no);

			if (!flag)
				break;
		}
		System.out.println(count);

	}

	static void sum(int no) {
		count++;
		for (int k = 51; k < no; k++) {
			int peoplesum = 0;
			int countrycount = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == k) {
						countrycount++;
						peoplesum += arr[i][j];
					}
				}
			}
			int resetpeople = peoplesum / countrycount;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == k) {
						arr[i][j] = resetpeople;
					}
				}
			}

		}
	}

	static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(h, y, arr[h][y]));
		visit[h][y] = true;
		map[h][y] = no;
		boolean check = false;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
					continue;

				int chi = Math.abs(arr[tmp.h][tmp.y] - arr[ah][ay]);
				if (chi >= L && chi <= R) {
					q.add(new Data(ah, ay, arr[ah][ay]));
					visit[ah][ay] = true;
					map[ah][ay] = no;
					check = true;
					flag = true;
				}
			}
		}
		if (!check) {
			visit[h][y] = false;
			map[h][y] = 0;
		} else {
			no++;
		}

	}

	static class Data {
		int h;
		int y;
		int people;

		public Data(int h, int y, int people) {
			super();
			this.h = h;
			this.y = y;
			this.people = people;
		}

	}

}