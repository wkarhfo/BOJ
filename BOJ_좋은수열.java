import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_좋은수열 {
	static int N;
	static int[] arr = { 1, 2, 3 };
	static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		result = new int[N];
		dfs(0, 0);
	}

	static void dfs(int depth, int pre) {

		if (!check(depth))
			return;
		
		if (depth == N) {
			String s = "";
			for (int i = 0; i < result.length; i++) {
				s += result[i];
			}
			System.out.println(s);
			System.exit(0);
		}
		for (int i = 0; i < 3; i++) {
			if (arr[i] == pre)
				continue;
			result[depth] = arr[i];

			dfs(depth + 1, arr[i]);
		}
	}

	static boolean check(int depth) { // 중복이 있는지 체크
		for (int i = 2; i <= depth / 2; i++) {
			for (int j = 0; j <= depth - i; j++) {
				int count = 0;

				for (int k = j; k < j + i; k++) {
					if(k+i>=depth)
						continue;
					if (result[k] == result[k + i]) {
						count++;
					}
				}
				if (count == i)
					return false;
			}
		}
		return true;
	}

}
