import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 0의 갯수가 가장 적게 될때를 출력

public class BOJ_감시 {
	static int N, M;
	static int[][] arr;
	static int[][] reset;
	static List<Data> list;
	static Way[] walylist;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		reset = new int[N][M];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = reset[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] > 0 && arr[i][j] < 6) {
					list.add(new Data(i, j, arr[i][j]));
				}
			}
		}
		walylist = new Way[list.size()];
		dfs(0);
		System.out.println(MIN);

	}

	static void reset() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = reset[i][j];
			}
		}
	}

	// 상하좌우 함수만들기
	static void sang(int ah, int ay) {
		int temph = ah - 1;
		while (true) {
			if (temph < 0 || arr[temph][ay] == 6)
				break;
			if (arr[temph][ay] == 0)
				arr[temph][ay] = 9;
			temph--;
		}
	}

	static void ha(int ah, int ay) {
		int temph = ah + 1;
		while (true) {
			if (temph >= arr.length || arr[temph][ay] == 6)
				break;
			if (arr[temph][ay] == 0)
				arr[temph][ay] = 9;
			temph++;
		}
	}

	static void zwa(int ah, int ay) {
		int tempy = ay - 1;
		while (true) {
			if (tempy < 0 || arr[ah][tempy] == 6)
				break;
			if (arr[ah][tempy] == 0)
				arr[ah][tempy] = 9;
			tempy--;
		}
	}

	static void woo(int ah, int ay) {
		int tempy = ay + 1;
		while (true) {
			if (tempy >= arr[0].length || arr[ah][tempy] == 6)
				break;
			if (arr[ah][tempy] == 0)
				arr[ah][tempy] = 9;
			tempy++;
		}
	}

	static void dfs(int depth) {
		if (depth == list.size()) {
			for (int i = 0; i < list.size(); i++) {
				int ah = list.get(i).h;
				int ay = list.get(i).y;
				if (walylist[i].type == 1) {
					if (walylist[i].way == 1) { // 상 ^
						sang(ah, ay);
					} else if (walylist[i].way == 2) { // 오른쪽
						woo(ah, ay);

					} else if (walylist[i].way == 3) { // 아래
						ha(ah, ay);
					} else if (walylist[i].way == 4) { // 왼쪽
						zwa(ah, ay);
					}
				} else if (walylist[i].type == 2) {
					if (walylist[i].way == 1) { // 좌우방향
						zwa(ah, ay);
						woo(ah, ay);
					} else { // 상하방향
						sang(ah, ay);
						ha(ah, ay);
					}
				} else if (walylist[i].type == 3) {
					if (walylist[i].way == 1) { // 상우
						sang(ah, ay);
						woo(ah, ay);
					} else if (walylist[i].way == 2) { // 우하
						woo(ah, ay);
						ha(ah, ay);
					} else if (walylist[i].way == 3) { // 하좌
						ha(ah, ay);
						zwa(ah, ay);
					} else {// 좌상
						zwa(ah, ay);
						sang(ah, ay);
						
					}

				} else if (walylist[i].type == 4) {
					if (walylist[i].way == 1) { // 우상좌
						woo(ah, ay);
						sang(ah, ay);
						zwa(ah, ay);
					} else if (walylist[i].way == 2) { //상 우 하
						sang(ah, ay);
						woo(ah, ay);
						ha(ah, ay);
					} else if (walylist[i].way == 3) { // 우 하 좌
						woo(ah, ay);
						ha(ah, ay);
						zwa(ah, ay);
					} else { // 하좌상
						ha(ah, ay);
						zwa(ah, ay);
						sang(ah, ay);
					}
				} else { // 상하좌우
					if (walylist[i].way == 1) { // 상하좌우
						sang(ah, ay);
						ha(ah, ay);
						zwa(ah, ay);
						woo(ah, ay);
					}
				}

			}
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 0)
						count++;
				}
			}
		
			MIN = Math.min(MIN, count);
			reset();
			return;
		}
		if (list.get(depth).type == 1) { // 1번 cctv
			for (int i = 0; i < 4; i++) {
				if (i == 0) { // 위쪽방향만 cctv감시
					walylist[depth] = new Way(1, 1); // 1:위쪽방향
				} else if (i == 1) { // 오른쪽 방향만 cctv감시
					walylist[depth] = new Way(1, 2); // 2:오른쪽방향
				} else if (i == 2) {
					walylist[depth] = new Way(1, 3); // 3:아래쪽방향
				} else {
					walylist[depth] = new Way(1, 4); // 4:왼쪽방향
				}
				dfs(depth + 1);
			}
		} else if (list.get(depth).type == 2) { // 2 번 cctv
			for (int i = 0; i < 2; i++) {
				if (i == 0) {
					walylist[depth] = new Way(2, 1); // 1:좌우방향
				} else {
					walylist[depth] = new Way(2, 2); // 2:상하방향
				}
				dfs(depth + 1);
			}

		} else if (list.get(depth).type == 3) {
			for (int i = 0; i < 4; i++) {
				if (i == 0) {
					walylist[depth] = new Way(3, 1); // 1:상우방향
				} else if (i == 1) {
					walylist[depth] = new Way(3, 2); // 2:우하
				} else if (i == 2) {
					walylist[depth] = new Way(3, 3); // 3:하좌
				} else {
					walylist[depth] = new Way(3, 4); // 4 :좌상
				}
				dfs(depth + 1);
			}
		} else if (list.get(depth).type == 4) {
			for (int i = 0; i < 4; i++) {
				if (i == 0) {
					walylist[depth] = new Way(4, 1); // 1:좌상우방향
				} else if (i == 1) {
					walylist[depth] = new Way(4, 2); // 2:상우하
				} else if (i == 2) {
					walylist[depth] = new Way(4, 3); // 3:우하좌
				} else {
					walylist[depth] = new Way(4, 4); // 4 :하좌상
				}
				dfs(depth + 1);
			}
		} else if (list.get(depth).type == 5) {
			walylist[depth] = new Way(5, 1);// 1:상하좌우
			dfs(depth + 1);
		}

	}

	static class Data {
		int h;
		int y;
		int type;
		public Data(int h, int y, int type) {
			super();
			this.h = h;
			this.y = y;
			this.type = type;
		}
	}

	static class Way {
		int type;// 1,2,3,4,5번 cctv인지 판단
		int way;// 어느 방향을 바라보는지 판단
		public Way(int type, int way) {
			super();
			this.type = type;
			this.way = way;
		}

	}
}
