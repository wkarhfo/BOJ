import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 *  bfs를 돌면서 같은거리에 있는 값들을 모두 넣어서 비교후 가장 위, 가장 왼쪽에 잇는 값을 min좌표로 정한다..
 *  ....bfs에 대한 이해를 좀더 해야하는 문제... 
 *
 */
public class BOJ_아기상어 {
	static int N;
	static int[][] arr;
	static int sharksize = 2;
	static int[][] distance;
	static int eat = 0;
	static Data start;
	static int minH;
	static int minY;
	static int MIN;// 최소 이동거리 비교값
	static boolean flag;

	static boolean[][] visit;
	static int count = 0; // 총 이동거리
	static int[] dh = { -1, 0, 1, 0 }; // 상 좌 하 우
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N][N];
		distance=new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					start = new Data(i, j, 0);
					arr[i][j] = 0;
				}
			}
		}

		while (true) {

			flag=false;
			minH = Integer.MAX_VALUE;
			minY = Integer.MAX_VALUE;
			MIN = Integer.MAX_VALUE;
			visit = new boolean[N][N];
			bfs(start);
			if(!flag)
				break;
			eat++;
			if (eat == sharksize) {
				sharksize++;
				eat = 0;
			}
			count += distance[minH][minY];
			arr[minH][minY] = 0;
			start = new Data(minH, minY, 0);

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
//
//			System.out.println();

		}
		System.out.println(count);

	}

	private static void bfs(Data st) {
		Queue<Data> q = new LinkedList<>();
		q.add(st);
		visit[st.h][st.y] = true;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr.length || visit[ah][ay]
						|| arr[ah][ay] > sharksize)
					continue;

				if (arr[ah][ay] > 0 && arr[ah][ay] < sharksize) {
					if (MIN > tmp.move + 1) {
						minH = ah;
						minY = ay;
						MIN = tmp.move + 1;
					} else if (MIN == tmp.move + 1) {
						if (minH > ah) {
							minH = ah;
							minY = ay;
						} else if (minH == ah) {
							if (minY > ay) {
								minH = ah;
								minY = ay;
							}
						}
					}
					flag=true;
				}
				distance[ah][ay]=tmp.move+1;
				q.add(new Data(ah, ay, tmp.move + 1));
				visit[ah][ay] = true;

			}
		}

	}

	static class Data {
		int h;
		int y;
		int move;

		public Data(int h, int y, int move) {
			super();
			this.h = h;
			this.y = y;
			this.move = move;
		}

	}
}
