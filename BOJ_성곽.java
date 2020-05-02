import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1. 이 성에 있는 방의 갯수 2. 가장 넓은 방의 넓이 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
 */
public class BOJ_성곽 {
	static int n, m;
	static int[][] arr;
	static boolean[][] visit;
	static int[] dh = { 1, 0, -1, 0 }; // 하 우 상 좌
	static int[] dy = { 0, 1, 0, -1 }; // 하 우 상 좌
	static int roomCount = 0; // 방갯수
	static int maxRoom = 0;
	static int sumMaxroom = 0;
	 static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 열
		m = Integer.parseInt(st.nextToken());// 행
		arr = new int[m][n];
		visit = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit[i][j]) {
					bfs(i, j);
					roomCount++;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				visit = new boolean[m][n];
				bfs(i, j);
				int tempsum=count;
				for (int k = 0; k < 4; k++) {
					int ah = i + dh[k];
					int ay = j + dy[k];
					if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
						continue;
					bfs(ah, ay);
					sumMaxroom=Math.max(sumMaxroom, tempsum+count);
				}
			}
		}
		System.out.println(roomCount);
		System.out.println(maxRoom);
		System.out.println(sumMaxroom);
	}

	static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(h, y));
		visit[h][y] = true;
		count = 0;
		while (!q.isEmpty()) {
			count++;
			Data tmp = q.poll();
			String binary = Integer.toBinaryString(arr[tmp.h][tmp.y]);
			while (binary.length() != 4) {
				String s = "0";
				binary = s + binary;
			}
			for (int i = 0; i < binary.length(); i++) {
				if (binary.charAt(i) == '0') {
					int ah = tmp.h + dh[i];
					int ay = tmp.y + dy[i];
					if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
						continue;
					q.add(new Data(ah, ay));
					visit[ah][ay] = true;
				}
			}
			maxRoom = Math.max(maxRoom, count);
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
