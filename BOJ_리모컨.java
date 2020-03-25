import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_리모컨 {
	static int N, M;
	static int numberCnt;
	static boolean[] num;
	static int MIN = Integer.MAX_VALUE;
	static int last = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		if (M == 0) {
			int tmp=Math.abs(N-100);
			if(tmp<3)
				System.out.println(Math.abs(N - 100));
			else {
				System.out.println(Integer.toString(N).length());
			}
		} else if (M == 10) {
			System.out.println(Math.abs(N - 100));
		}
		else {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			numberCnt = Integer.toString(N).length();
			num = new boolean[10];
			for (int i = 0; i < M; i++) {
				int broke = Integer.parseInt(st.nextToken());
				num[broke] = true;
			}
			if (M == 100) {
				System.out.println(0);
			} else {
				int a = Math.abs(N - 100);
				int b = find(N);
				b += Math.abs(N - last);
				int result = Math.min(a, b);
				
				System.out.println(result);
			}
		}

	}

	private static int find(int tmp) {
		for (int i = 0; i <= 1000000; i++) {
			if (isokay(i)) {
				int temp = Math.abs(N - i);
				if (temp == MIN) {
					int a = Integer.toString(temp).length();
					int b = Integer.toString(MIN).length();
					if (b < a) {
						continue;
					}
				} else if (temp < MIN) {
					MIN = temp;
					last = i;
				} else {
					continue;
				}
			}
		}

		return Integer.toString(last).length();
	}

	private static boolean isokay(int n) {
		String s = Integer.toString(n);
		for (int i = 0; i < s.length(); i++) {
			int idx = Character.getNumericValue(s.charAt(i));
			if (num[idx]) {
				return false;
			}
		}
		return true;
	}
}
