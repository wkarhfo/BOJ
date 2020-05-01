import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_치킨배달_다르게풀이 {
	static int N, M;
	static int[][] arr;
	static ArrayList<Data> chicken;
	static ArrayList<Data> people;
	static Data[] list;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		chicken = new ArrayList<>();
		people = new ArrayList<>();
		list = new Data[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					people.add(new Data(i, j));
				} else if (arr[i][j] == 2) {
					chicken.add(new Data(i, j));
				}
			}
		}

		dfs(0, 0);
		System.out.println(MIN);

	}

	static void dfs(int start, int depth) {
		if (depth == M) {
			int count = 0;
			for (int i = 0; i < people.size(); i++) {
				int temp = Integer.MAX_VALUE;
				for (int j = 0; j < list.length; j++) {
					temp = Math.min(temp,
							Math.abs(people.get(i).h - list[j].h) + Math.abs(people.get(i).y - list[j].y));
				}
				count += temp;
			}
			MIN = Math.min(MIN, count);
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			list[depth] = chicken.get(i);
			dfs(i + 1, depth + 1);
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
