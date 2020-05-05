import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_상범빌딩 {
	static int L, R, C;
	static char[][][] arr;
	static boolean[][][] visit;
	static int[] dh = { 0, 0, 1, -1, 0, 0 };// 동서남북상하
	static int[] dy = { 1, -1, 0, 0, 0, 0 };
	static int[] dl = { 0, 0, 0, 0, -1, 1 };
	static int startL, startR, startC;
	static int endL, endR, endC;
	static int time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if (L == 0 && R == 0 && C == 0) {
				break;
			}
			arr = new char[L][R][C];
			visit = new boolean[L][R][C];

			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					arr[i][j] = br.readLine().toCharArray();
				}
				String s=br.readLine();
			}
			
			for (int k = 0; k < L; k++) {
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (arr[k][i][j] == 'S') {
							startL = k;
							startR = i;
							startC = j;
						} else if (arr[k][i][j] == 'E') {
							endL = k;
							endR = i;
							endC = j;
						}
					}
				}
			}

			time = 0;
			boolean flag = false;
			Queue<Data> q = new LinkedList<>();
			q.add(new Data(startL, startR, startC,0));
			visit[startL][startR][startC] = true;
			while (!q.isEmpty()) {
				Data tmp = q.poll();
				if (tmp.h == endR && tmp.y == endC && tmp.l == endL) {
					time=tmp.time;
					flag = true;
					break;
				}
				for (int k = 0; k < 6; k++) {
					int al = tmp.l + dl[k];
					int ah = tmp.h + dh[k];
					int ay = tmp.y + dy[k];
					if (ah < 0 || ah >= R || ay < 0 || ay >= C || al < 0 || al >= L || visit[al][ah][ay]
							|| arr[al][ah][ay] == '#')
						continue;

					visit[al][ah][ay] = true;
					q.add(new Data(al, ah, ay,tmp.time+1));
				}
			}
			
			if (flag) {
				sb.append("Escaped in " + time + " minute(s).\n");
			} else {
				sb.append("Trapped!\n");
			}

		}
		System.out.println(sb);
	}

	static class Data {
		int l; // 높이
		int h; // 행
		int y; // 열
		int time;

		public Data(int l, int h, int y, int time) {
			super();
			this.l = l;
			this.h = h;
			this.y = y;
			this.time = time;
		}
	}
}
