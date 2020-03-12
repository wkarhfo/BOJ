import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_외판원순회2 {
	static int[][] arr;
	static boolean[] visit;
	static int N;
	static int MIN;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visit = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		MIN = Integer.MAX_VALUE;
		dfs(0, 1, 0, 0);
		System.out.println(MIN);
	}

	private static void dfs(int h, int y, int cnt, int sum) {
		if (cnt == arr.length) {
			if(h==0) {
				MIN = Math.min(MIN, sum);
				return;
			}
			return;
		}
		
		if (sum > MIN) {
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				if (arr[h][i] == 0)
					continue;
				visit[i] = true;
				dfs(i, y, cnt + 1, sum + arr[h][i]);
				visit[i] = false;

			}
		}
	}
}
