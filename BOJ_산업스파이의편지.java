import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_산업스파이의편지 {
	static int[] arr;
	static int cnt = 0;
	static boolean[] visit;
	static int count;
	static ArrayList<Integer> list;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		check = new boolean[10000000];
		check[0] = check[1] = false;
		for (int i = 2; i < 10000000; i++) {
			check[i] = true;
		}
		for (int i = 2; i * i < 10000000; i += 1) {
			for (int j = i * i; j < 10000000; j += i) {
				check[j] = false; // 2를 제외한 2의 배수 false
			}
		}
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			String s = br.readLine();
			arr = new int[s.length()];
			for (int i = 0; i < s.length(); i++) {
				arr[i] = Character.getNumericValue(s.charAt(i));
			}

//			System.out.println(Arrays.toString(arr));
			count = 0;
			list = new ArrayList<>();

			for (int i = 1; i <= s.length(); i++) {
				cnt = i;
				visit = new boolean[arr.length];
				dfs(0, "");

			}
			System.out.println(count);
		}
	}

	static void dfs(int depth, String s) {
		if (depth == cnt) {
//				System.out.println(Arrays.toString(result));

			int number = Integer.parseInt(s);
//				System.out.println(number);

			if (check[number] && !list.contains(number)) {
				list.add(number);
				count++;
			}

			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (visit[i])
				continue;

			visit[i] = true;
			dfs(depth + 1, s + arr[i]);
			visit[i] = false;
		}
	}

}
