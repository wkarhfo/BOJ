import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_소문난칠공주 {
	static char[][] arr;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] temp;
	static int result = 0;
	static boolean[][] visit;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[5][5];
		for (int i = 0; i < 5; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		temp = new int[7];
		dfs(0, 0, 0);
		System.out.println(result);
	}

	static void dfs(int start, int depth, int cnt) {
		if (depth == 7) {
			if (cnt < 4)
				return;
			visit = new boolean[5][5];
			if (bfs(temp[0] / 5, temp[0] % 5)) {
				System.out.println(Arrays.toString(temp));
				result++;
			}
			return;
		}
		for (int i = start; i < 25; i++) {
			char cmp = arr[i / 5][i % 5];
			temp[depth] = i;
			if (cmp == 'S')
				dfs(i + 1, depth + 1, cnt + 1);
			else
				dfs(i + 1, depth + 1, cnt);
		}
	}

	static boolean bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		count = 1;
		q.add(new Data(h, y));
		visit[h][y] = true;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			if (count == 7) {
				return true;
			}
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
					continue;
				for (int m = 0; m < temp.length; m++) {
					if (ah == temp[m] / 5 && ay == temp[m] % 5) {
						q.add(new Data(ah, ay));
						count++;
						visit[ah][ay] = true;
					}
				}
			}
		}
		return false;
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
