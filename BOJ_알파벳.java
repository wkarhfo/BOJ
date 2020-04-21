import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_알파벳 {
	static int R, C;
	static char[][] arr;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit;
	static boolean[] alpha = new boolean[26];
	static int count = 1;
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		visit[0][0] = true;
		alpha[arr[0][0] - 'A'] = true;
		dfs(0, 0);
		System.out.println(MAX);
	}

	static void dfs(int h, int y) {
		MAX = Math.max(MAX, count);
		
		for (int k = 0; k < 4; k++) {
			int ah = h + dh[k];
			int ay = y + dy[k];
			if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
				continue;
			if (alpha[arr[ah][ay] - 'A'])
				continue;

			count++;
			alpha[arr[ah][ay] - 'A'] = true;
			visit[ah][ay] = true;
			dfs(ah, ay);
			count--;
			visit[ah][ay] = false;
			alpha[arr[ah][ay] - 'A'] = false;

		}
	}
}
