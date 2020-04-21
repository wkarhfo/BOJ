import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_뿌요뿌요 {
	static char[][] arr = new char[12][6];
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int crush = 0;
	static boolean[][] visit;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		while (true) {
			flag = false;
			visit = new boolean[12][6];
			for (int i = 12 - 1; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (arr[i][j] != '.' && !visit[i][j]) {
						bfs(i, j); // 연쇄작용 일어나는것 체크
					}
				}
			}
			if (!flag) {
				break;
			}	
			drop();
			crush++;
		}

		System.out.println(crush);

	}

	static void drop() {

		for (int i = 0; i < 6; i++) {
			ArrayList<Character> list = new ArrayList<>();
			for (int j = 11; j >= 0; j--) {
				if (arr[j][i] != '.') {
					list.add(arr[j][i]);
					arr[j][i] = '.';
				}
			}

			int idx = 11;
			for (int j = 0; j < list.size(); j++) {
				arr[idx][i] = list.get(j);
				idx--;
			}

		}
	}

	static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(h, y));
		boolean[][] visit2 = new boolean[12][6];
		visit2[h][y] = true;
		int count = 1;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit2[ah][ay])
					continue;
				if (arr[ah][ay] != arr[tmp.h][tmp.y])
					continue;
				count++;
				visit2[ah][ay] = true;
				q.add(new Data(ah, ay));
			}
		}
		if (count >= 4) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (visit2[i][j]) {
						arr[i][j] = '.';
						visit[i][j] = true;
					}
				}
			}
			flag = true;
		}
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
