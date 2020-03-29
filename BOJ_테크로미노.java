import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_테크로미노 {
	static int h, y;
	static int[][] arr;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit;
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		arr = new int[h][y];
		visit = new boolean[h][y];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < y; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				visit[i][j] = true;
				dfs(i, j, 0, 0);
				dfs2(i, j, 0);
				visit[i][j] = false;
			}
		}

		System.out.println(MAX);

	}

	private static void dfs2(int h, int y, int sum) {
		// case1 ㅗ
		if (h - 1 >= 0 && y + 2 < arr[0].length) {
			MAX = Math.max(MAX, arr[h][y] + arr[h][y + 1] + arr[h][y + 2] + arr[h - 1][y + 1]);
		}
		// case2 ㅜ
		if (h + 1 < arr.length && y + 2 < arr[0].length) {
			MAX = Math.max(MAX, arr[h][y] + arr[h][y + 1] + arr[h][y + 2] + arr[h + 1][y + 1]);
		}
		// case3 ㅏ
		if (h + 2 < arr.length && y + 1 < arr[0].length) {
			MAX = Math.max(MAX, arr[h][y] + arr[h + 1][y] + arr[h + 2][y] + arr[h + 1][y + 1]);
		}
		// case4 ㅓ
		if (h + 2 < arr.length && y - 1 >= 0) {
			MAX = Math.max(MAX, arr[h][y] + arr[h + 1][y] + arr[h + 2][y] + arr[h + 1][y - 1]);
		}

	}

	private static void dfs(int h, int y, int depth, int sum) {
		if (depth == 4) {
			MAX = Math.max(MAX, sum);
			return;
		}
		for (int k = 0; k < 4; k++) {
			int ah = h + dh[k];
			int ay = y + dy[k];
			if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length||visit[ah][ay])
				continue;
			visit[ah][ay] = true;
			dfs(ah, ay, depth + 1, sum + arr[h][y]);
			visit[ah][ay] = false;

		}
	}

}
