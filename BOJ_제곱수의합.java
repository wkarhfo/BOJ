import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// dp를 이용하여 그전의 값들중에 가장 작은값을 계산하여 저장
public class BOJ_제곱수의합 {
	static int[] dp = new int[100000 + 1];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());

		for (int i = 1; i <= N; i++) {
			double doublesqrt = Math.sqrt(i);
			int tmp = (int) doublesqrt;
//			System.out.println(tmp);
			if (tmp == doublesqrt) {
				dp[i] = 1;
			} else {
				dp[i] = i;
				for (int j = 1; j * j <= i; j++)
					dp[i] = Math.min(1 + dp[i - j * j], dp[i]);
			}
		}
		System.out.println(dp[N]);
	}
}
