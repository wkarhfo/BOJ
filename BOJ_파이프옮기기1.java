import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_파이프옮기기1 {
	static int N;
	static int[][] arr;
	static int endh, endy;
	static int[] dh = { 0, 1, 1 }; // 우, 하, 대각선
	static int[] dy = { 1, 0, 1 };
	static int count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		endh = endy = N - 1; // 도착점
		// 2:우 3:하 4:우하
		dfs(0, 1, 2);
		System.out.println(count);
	}

	static void dfs(int h, int y, int type) {
		if (h == endh && y == endy) {
			count++;
			return;
		}

		for (int k = 0; k < 3; k++) {

			if (type == 2 && k == 1) { // 가로 ->하는 해당 안됨
				continue;
			} else if (type == 3 && k == 0) { // 세로-> 우는 해당안됨
				continue;
			} else if (type == 4) { // 대각선

			}

			if (ischeck(h, y, k)) {
				int ah = h + dh[k];
				int ay = y + dy[k];
				dfs(ah, ay, k + 2);
			}

		}
	}

	static boolean ischeck(int h, int y, int k) { // 1이 있는지 없는지 체크하는 함수
		int ah = h + dh[k];
		int ay = y + dy[k];
		if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length)
			return false;
		if (k == 0) { // 가로 방향으로 나아갈때
			if (arr[ah][ay] == 1)
				return false;
		} else if (k == 1) { // 세로 일때
			if (arr[ah][ay] == 1)
				return false;
		} else if (k == 2) { // 대각선 방향으로 나아갈때
			if (arr[ah][ay] == 1 || arr[ah - 1][ay] == 1 || arr[ah][ay - 1] == 1)
				return false;
		}
		return true;
	}

}
