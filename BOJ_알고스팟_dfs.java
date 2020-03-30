import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_알고스팟_dfs {
	static int hang, yeul;
	static int[][] arr;
	static int endh, endy;
	static boolean[][] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		yeul = Integer.parseInt(st.nextToken());
		hang = Integer.parseInt(st.nextToken());
		arr = new int[hang][yeul];
		visit = new boolean[hang][yeul];

		endh = hang - 1;
		endy = yeul - 1;

		for (int i = 0; i < hang; i++) {
			String s = br.readLine();
			for (int j = 0; j < yeul; j++) {
				arr[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}

		visit[0][0] = true;
		dfs(0, 0, 0);
		System.out.println(MIN);

	}

	private static void dfs(int h, int y, int sum) {

		if (h == endh && y == endy) {
			MIN = Math.min(MIN, sum);
			return;
		}
		for (int k = 0; k < 4; k++) {
			int ah = h + dh[k];
			int ay = y + dy[k];
			if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
				continue;
			if (MIN < sum)
				return;

			visit[ah][ay] = true;
			if (arr[ah][ay] == 1) {
				dfs(ah, ay, sum + 1);
			} else {
				dfs(ah, ay, sum);
			}
			visit[ah][ay] = false;
		}
	}

	static class Data {
		int h;
		int y;
		int broke;

		public Data(int h, int y, int broke) {
			super();
			this.h = h;
			this.y = y;
			this.broke = broke;
		}
	}

}
