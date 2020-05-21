import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_가르침 {
	static int N, K;
	static boolean[] alpha;
	static ArrayList<String> word;
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 단어 갯수
		K = Integer.parseInt(st.nextToken()); // 글자 수
		word = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			word.add(br.readLine());
		}

		alpha = new boolean[26];
		alpha['a' - 'a'] = true;
		alpha['n' - 'a'] = true;
		alpha['t' - 'a'] = true;
		alpha['i' - 'a'] = true;
		alpha['c' - 'a'] = true;

		if (K < 5)
			System.out.println(0);
		else if (K == 26)
			System.out.println(N);
		else {
			K -= 5;
			dfs(0, 0); // 알파벳 K개 뽑기
			System.out.println(MAX);
		}

	}

	private static void dfs(int start, int depth) {
		if (depth == K) {
			int count = 0;
			for (int i = 0; i < word.size(); i++) {
				String temp = word.get(i);
				boolean flag = true;
				for (int j = 4; j <= temp.length() - 4; j++) {
					if (!alpha[temp.charAt(j) - 'a']) {
						flag = false;
						break;
					}
				}
				if (flag) {
					count++;
				}

			}
			MAX = Math.max(MAX, count);
			return;
		}
		for (int i = start; i < 26; i++) {
			if (alpha[i])
				continue;
			alpha[i] = true;
			dfs(i + 1, depth + 1);
			alpha[i] = false;
		}
	}
}
