import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_스도쿠 {
	static int[][] arr = new int[9][9];
	static ArrayList<Data> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = s.charAt(j) - '0';
				if (arr[i][j] == 0) {
					list.add(new Data(i, j));
				}
			}
		}

		dfs(0);

	}

	static void dfs(int depth) {
		if (depth == list.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
			System.exit(0);;
		}
		for (int i = 1; i < 10; i++) {
			int ah = list.get(depth).h;
			int ay = list.get(depth).y;
			if (ishang(ah, ay, i) && isyeol(ah, ay, i) && isbox(ah, ay, i)) { // 행과 열,네모박스 검사하기
				arr[ah][ay] = i;
				dfs(depth + 1);
			}
			if (i == 9) {
				arr[ah][ay] = 0;
			}
		}

	}

	static boolean isbox(int ah, int ay, int num) {
		int h = ah / 3 * 3;
		int y = ay / 3 * 3;

		for (int i = h; i < h + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if (arr[i][j] == num)
					return false;
			}
		}
		return true;
	}

	static boolean isyeol(int ah, int ay, int num) {
		for (int k = 0; k < 9; k++) {
			if (arr[k][ay] == num)
				return false;
		}
		return true;
	}

	static boolean ishang(int ah, int ay, int num) {
		for (int k = 0; k < 9; k++) {
			if (arr[ah][k] == num)
				return false;
		}
		return true;
	}

	static class Data {
		int h;
		int y;

		public Data(int h, int y) {
			super();
			this.h = h;
			this.y = y;
		}

	}
}
