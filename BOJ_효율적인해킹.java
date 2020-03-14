
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_효율적인해킹 {

	static int n, m;
	static ArrayList<Integer>[] list;
	static int[] dp;
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];
		dp = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		for (int i = 1; i <= n; i++) {
			visited = new boolean[n + 1];
			solve(i);
		}

		for (int i = 1; i <= n; i++) {
			max = Math.max(max, dp[i]);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= n; i++) {
			if (dp[i] == max)
				sb.append(i + " ");
		}
		System.out.println(sb.toString());
	}

	static void solve(int cur) {
		visited[cur] = true;

		for (int next : list[cur]) {
			if (visited[next])
				continue;
			dp[next]++;
			solve(next);
		}
	}

}
