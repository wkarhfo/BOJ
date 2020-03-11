import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_카드구매하기 {
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		dp = new int[1000 + 1];
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=i;j++) {
				dp[i]=Math.max(dp[i],arr[j]+dp[i-j]);
			}
		}
		System.out.println(dp[N]);
		
	}

}
