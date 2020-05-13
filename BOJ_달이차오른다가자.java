import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_달이차오른다가자 {
	static int N, M;
	static char[][] arr;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Data start;
	static boolean[][][] visit;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '0') {
					start = new Data(i, j);
				}
			}
		}
		result = Integer.MAX_VALUE;
		// 비트마스킹을 이용해서 000000~111111 (a,b,c,d,e,f 6개의 키) -- 최대의 경우 모든 키를 갖고있는 경우는 63이므로
		visit = new boolean[N][M][64];
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(start.h, start.y, 0, 0));
		visit[start.h][start.y][0] = true;
		
		outer:while (!q.isEmpty()) {
			Data tmp = q.poll();
//			System.out.println(tmp.h+","+tmp.y+":"+tmp.count+" key:"+tmp.key);
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || arr[ah][ay] == '#')
					continue;
				if (arr[ah][ay] >= 'a' && arr[ah][ay] <= 'f') { // 열쇠가 있는곳일 경우
					int tempkey = tmp.key | (1 << (arr[ah][ay] - 'a'));
					if (visit[ah][ay][tempkey])
						continue;
					visit[ah][ay][tempkey] = true;
					q.add(new Data(ah, ay, tmp.count + 1, tempkey));

				} else if (arr[ah][ay] >= 'A' && arr[ah][ay] <= 'F') { // 문이 있는 곳일 경우
					int tempkey = tmp.key & (1 << (arr[ah][ay] - 'A'));
					if (tempkey == 0 || visit[ah][ay][tmp.key])
						continue;
					visit[ah][ay][tmp.key] = true;
					q.add(new Data(ah, ay, tmp.count + 1, tmp.key));

				} else if (arr[ah][ay] == '1') { // 출구 인 경우
					result = tmp.count+1;
					break outer;
				} else { // 그외 나머지 인 경우
					if (visit[ah][ay][tmp.key])
						continue;
					visit[ah][ay][tmp.key] = true;
					q.add(new Data(ah, ay, tmp.count + 1, tmp.key));

				}
			}
		}
		if (result == Integer.MAX_VALUE) { // 탈출 하지 못한 경우
			System.out.println(-1);
		} else {
			System.out.println(result); // 탈출한 경우
		}

	}

	static class Data {
		int h;
		int y;
		int count;
		int key;

		public Data(int h, int y, int count, int key) {
			super();
			this.h = h;
			this.y = y;
			this.count = count;
			this.key = key;
		}

		public Data(int h, int y) {
			super();
			this.h = h;
			this.y = y;
		}
	}
}
