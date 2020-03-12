import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_123더하기dfs {
	static int N;
	static int[] arr = { 1, 2, 3 };
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			count = 0;
			for (int i = 1; i <= 3; i++) {
				dfs(i, i);
			}
			System.out.println(count % 1000000009);
		}
	}

	private static void dfs(int idx, int sum) {
		if (sum == N) {
			count++;
			return;
		}

		for (int k = 0; k < 3; k++) {
			if (sum + arr[k] > N)
				continue;
			dfs(k, sum + arr[k]);
		}
	}
}
