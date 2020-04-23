import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_가장긴증가하는수열 {
	static int A;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = Integer.parseInt(br.readLine().trim());
		arr = new int[A];
		dp = new int[A];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = arr[0];
		int idx = 0;
		for (int i = 1; i < A; i++) {
			if (dp[idx] < arr[i]) {
				dp[++idx] = arr[i];
			} else {
				int temp = lower_bound(dp, idx, arr[i]);
				dp[temp] = arr[i];
			}

		}
		System.out.println(idx+1);

	}

	static int lower_bound(int[] dp, int end, int n) {
		int start = 0;
		while (start < end) {
			int mid = (start + end) / 2;
			if (dp[mid] >= n) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return end;
	}
}
