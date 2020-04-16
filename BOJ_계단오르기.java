import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_계단오르기 {
	static int n;
	static int[] dp;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		dp = new int[300 + 1];
		arr = new int[300 + 1];
		for (int i = 1; i < n + 1; i++) {
			arr[i] = Integer.parseInt(br.readLine().trim());
		}
		dp[1] = arr[1];
		dp[2] = arr[1]+arr[2];
		for (int i = 3; i < n + 1; i++) {
			dp[i] = Math.max(dp[i - 2] + arr[i], arr[i] + arr[i - 1] + dp[i - 3]);
		}
		System.out.println(dp[n]);
	}
}
