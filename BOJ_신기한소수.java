import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_신기한소수 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		dfs(0, "");
	}

	static void dfs(int depth, String num) {
		if (depth == N) {
			System.out.println(num);
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (depth == 0) {
				if (i==1||i == 4 || i == 6 || i == 8 || i == 9)
					continue;
			}
			if (!isPrime(Integer.parseInt(num + i)))
				continue;

			dfs(depth + 1, num + i);
		}

	}

	static boolean isPrime(int num) {
		for (int i = 2; i <= num/2; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}
}
