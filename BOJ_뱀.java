import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_뱀 {
	static int N, K, L;
	static int[][] arr;
	static ArrayList<Dir> dir;
	static ArrayList<Data> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N][N];
		K = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a - 1][b - 1] = -1; // 사과의 위치에는 -1
		}
		L = Integer.parseInt(br.readLine().trim());
		dir = new ArrayList<>(); // 방향전환을 담은 리스트
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			char b = st.nextToken().charAt(0); // 단일 스트링 char형으로 변환
			dir.add(new Dir(a, b));
		}

		list = new ArrayList<>();
		list.add(new Data(0, 0));// 머리 (시작점)
		int count = 0;
		while (true) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				if (list.get(i).h < 0 || list.get(i).h >= arr.length || list.get(i).y < 0
						|| list.get(i).y >= arr[0].length) {
					System.out.println(count);
					break;
				}
				
				
				
				
			}
			count++;
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

	static class Dir {
		int time;
		char way;

		public Dir(int time, char way) {
			super();
			this.time = time;
			this.way = way;
		}

	}
}
