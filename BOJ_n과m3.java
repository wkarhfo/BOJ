import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_n과m3 {
	static int N, M;
	static int[] arr;
	static int[] result;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}
		dfs(0);
		System.out.println(sb);

	}

	private static void dfs(int depth) {
		if (depth == result.length) {
			for (int i = 0; i < result.length; i++) {
				if (i == result.length - 1) {
					sb.append(result[i]);
				} else
					sb.append(result[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			result[depth] = arr[i];
			dfs(depth + 1);
		}
	}
}
