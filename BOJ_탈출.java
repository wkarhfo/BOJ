import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_탈출 {
	static char[][] arr;
	static Queue<Data> q;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit;
	static boolean[][] visit2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int hang = Integer.parseInt(st.nextToken());
		int yeul = Integer.parseInt(st.nextToken());
		arr = new char[hang][yeul];
		visit = new boolean[hang][yeul];
		visit2 = new boolean[hang][yeul];
		
		for (int i = 0; i < hang; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = st.nextToken().toCharArray();
		}
		q = new LinkedList<>();
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < yeul; j++) {
				if (arr[i][j] == '*') {
					q.add(new Data(i, j, 1));
				}
			}
		}
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < yeul; j++) {
				if (arr[i][j] == 'S') {
					q.add(new Data(i, j, 2, 0));
				}
			}
		}
		while (!q.isEmpty()) {
			int t = q.size();
			for (int i = 0; i < t; i++) {
				Data tmp = q.poll();
				if (tmp.type == 1) {
					visit2[tmp.h][tmp.y]=true;
					for (int k = 0; k < 4; k++) {
						int ah = tmp.h + dh[k];
						int ay = tmp.y + dy[k];
						if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || arr[ah][ay] == 'D'
								|| arr[ah][ay] == 'X'||visit2[ah][ay]==true)
							continue;
						visit2[ah][ay]=true;
						arr[ah][ay] = '*';
						q.add(new Data(ah, ay, 1));
					}

				} else {
					visit[tmp.h][tmp.y] = true;
					if (arr[tmp.h][tmp.y] == 'D') {
						System.out.println(tmp.cnt);
						return;
					}
					for (int k = 0; k < 4; k++) {
						int ah = tmp.h + dh[k];
						int ay = tmp.y + dy[k];
						if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || arr[ah][ay] == 'X'
								|| arr[ah][ay] == '*'||visit[ah][ay]==true)
							continue;
						visit[ah][ay] = true;
						q.add(new Data(ah, ay, 2, tmp.cnt + 1));
					}
				}

			}

		}
		System.out.println("KAKTUS");

	}

	static class Data {
		int h;
		int y;
		int type;
		int cnt;

		public Data(int h, int y, int type, int cnt) {
			this.h = h;
			this.y = y;
			this.type = type;
			this.cnt = cnt;
		}

		public Data(int h, int y, int type) {
			this.h = h;
			this.y = y;
			this.type = type;
		}

	}
}
