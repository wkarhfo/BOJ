import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_가르침_비트마스킹 {
	static int N, K;
	static ArrayList<String> word;
	static int MAX = Integer.MIN_VALUE;
	static int alpha = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 단어 갯수
		K = Integer.parseInt(st.nextToken()); // 글자 수
		word = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			word.add(br.readLine());
		}
		alpha |= 1 << 'a' - 'a';
		alpha |= 1 << 'n' - 'a';
		alpha |= 1 << 't' - 'a';
		alpha |= 1 << 'i' - 'a';
		alpha |= 1 << 'c' - 'a';
//		System.out.println((4 & 1 << 2));
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
				String tmp = word.get(i);
				boolean flag = true;
				for (int j = 4; j <= tmp.length() - 4; j++) {
					if ((1 << (tmp.charAt(j) - 'a') & alpha) == 0) {
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
			if ((alpha & 1 << i) == 1<<i) //해당 자리로 이동해서 출력하면 그값이 나온다!!!!
				continue;
			alpha |= 1 << i;
			dfs(i + 1, depth + 1);
			alpha &= ~(1 << i);
		}
	}
}
