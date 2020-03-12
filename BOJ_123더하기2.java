import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_123더하기2 {
	static int N;
	static long mod=1000000009L;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		long[] dp = new long[1000000 + 1];
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			for (int i = 4; i <= 1000000; i++) {
				dp[i] = ((dp[i - 1]%mod) + (dp[i - 2]%mod) + (dp[i - 3]%mod)) % mod;
			}
			System.out.println(dp[N]);
		}
	}
}
