import java.io.IOException;
import java.util.Scanner;

public class BOJ_2xn타일링 {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		dp = new int[1000 + 1];

		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}

		System.out.println(dp[N]);

	}

}
