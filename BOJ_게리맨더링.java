import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_게리맨더링 {
	static int N;
	static int[] arr;
	static int[] visit;
	static int[][] list;
	static boolean[] temp;
	static int people1, people2;
	static int MIN = Integer.MAX_VALUE;
	static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		list = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				list[i][tmp] = 1;
			}
		}

		for (int i = 1; i <= N / 2; i++) {
			visit = new int[N + 1]; 
			dfs(1, 0, i);
		}
		if(MIN==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(MIN);
		}
	}

	static void dfs(int start, int depth, int dest) {
		if (depth == dest) {
			temp = new boolean[N + 1];
			people1 = 0;
			people2 = 0;
			sum = 0;
			for (int i = 1; i < visit.length; i++) {
				if (visit[i] == 1 && !temp[i]) {
					gray(i);
					break;
				}
			}
			people1 = sum;
			sum = 0;
			for (int i = 1; i < visit.length; i++) {
				if (visit[i] == 0 && !temp[i]) {
					gray(i);
					break;
				}
			}
			people2 = sum;
			for (int i = 1; i < temp.length; i++) {
				if (!temp[i])
					return;
			}
			MIN=Math.min(MIN, Math.abs(people1-people2));
			return;

		}
		for (int i = start; i < N + 1; i++) {
			visit[i] = 1;
			dfs(i + 1, depth + 1, dest);
			visit[i] = 0;
		}
	}

	static void gray(int idx) {
		temp[idx] = true;
		sum += arr[idx];
		for (int i = 1; i < N + 1; i++) {
			if (!temp[i] && visit[i] == visit[idx] && list[idx][i] == 1)
				gray(i);
		}

	}

}
