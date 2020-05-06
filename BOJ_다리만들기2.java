import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_다리만들기2 {
	static int N, M;
	static int[][] arr;
	static boolean[][] visit;
	static int num;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Dir> list;
	static int[] reset;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					arr[i][j] = -1;
				}
			}
		}

		num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == -1 && !visit[i][j]) {
					bfs(i, j);
					num++;
				}
			}
		}
		list = new ArrayList<>();
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && arr[i][j] > 0) {
					bfs2(i, j);
				}
			}
		}
		Collections.sort(list, new Comparator<Dir>() {
			@Override
			public int compare(Dir o1, Dir o2) {
				return Integer.compare(o1.weight, o2.weight);
			}
		});
		reset = new int[num];
		for (int i = 1; i < num; i++)
			reset[i] = i;

		check = new boolean[num];
		int result = 0;
		int count = num - 2;

		for (int i = 0; i < list.size(); i++) {
			int a = findset(list.get(i).start);
			int b = findset(list.get(i).dest);
			if (a == b)
				continue;
			if (count == 0)
				break;

			uninon(a, b);
			result += list.get(i).weight;
			count--;
		}
		boolean flag = true;
		int tmp = findset(1);
		for (int i = 2; i < check.length; i++) {
			if (tmp != findset(i)) {
				flag = false;
				break;
			}
		}
		if (!flag)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	static void uninon(int a, int b) {
		a = findset(a);
		b = findset(b);
		if (a != b)
			reset[b] = a;
	}

	static int findset(int num) {
		if (reset[num] == num)
			return num;
		int temp = findset(reset[num]);
		reset[num] = temp;
		return temp;
	}

	static void bfs2(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(h, y));
		visit[h][y] = true;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay])
					continue;
				if (arr[ah][ay] == 0) {
					int count = 1;
					while (true) {
						ah = ah + dh[k];
						ay = ay + dy[k];
						if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length) 
							break;
						
						else if (count == 1 && arr[ah][ay] > 0)
							break;
						else if (count > 1 && arr[ah][ay] == arr[h][y])
							break;
						else if (count > 1 && arr[ah][ay] > 0 && arr[ah][ay] != arr[h][y]) {
							list.add(new Dir(arr[h][y], arr[ah][ay], count));
							break;
						}
						count++;
					}
					continue;
				}
				visit[ah][ay] = true;
				q.add(new Data(ah, ay));
			}
		}
	}

	static void bfs(int h, int y) {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(h, y));
		visit[h][y] = true;
		arr[h][y] = num;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int k = 0; k < 4; k++) {
				int ah = tmp.h + dh[k];
				int ay = tmp.y + dy[k];
				if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[ah][ay] || arr[ah][ay] == 0)
					continue;
				visit[ah][ay] = true;
				arr[ah][ay] = num;
				q.add(new Data(ah, ay));
			}
		}

	}

	static class Dir {
		int start;
		int dest;
		int weight;

		public Dir(int start, int dest, int weight) {
			super();
			this.start = start;
			this.dest = dest;
			this.weight = weight;
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
