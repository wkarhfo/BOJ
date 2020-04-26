import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_야구 {
	static int N;
	static int[][] arr;
	static int[] order;
	static boolean[] visit;
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N][9 + 1];
		order = new int[9 + 1];
		visit = new boolean[9 + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(1);
		System.out.println(MAX);
	}

	static void dfs(int depth) {
		if (depth == 10) {
//			System.out.println(Arrays.toString(order));
			int score = 0;
			int idx = 1; // 첫이닝은 1번부터 시작
			boolean[] base = new boolean[3 + 1]; // 시작할때 주자는 한명도 없기에 0

			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
				Arrays.fill(base, false);
				int out = 0;
				while (true) {
					if (out == 3) // 아웃이 3번이면 이닝이 끝
						break;
					
					if (arr[i][order[idx]] == 0) {
						out++;
					} else if (arr[i][order[idx]] == 1) { // 1루타
						if (base[3]) {
							base[3] = false;
							score++;
						}
						if (base[2]) {
							base[3] = true;
							base[2] = false;
						}
						if (base[1]) {
							base[2] = true;
							base[1] = false;
						}
						base[1] = true;
					} else if (arr[i][order[idx]] == 2) { // 2루타
						if (base[3]) {
							base[3] = false;
							score++;
						}
						if (base[2]) {
							base[2] = false;
							score++;
						}
						if (base[1]) {
							base[3] = true;
							base[1] = false;
						}
						base[2] = true;
					} else if (arr[i][order[idx]] == 3) { // 3루타
						if (base[3]) {
							base[3] = false;
							score++;
						}
						if (base[2] ) {
							base[2] = false;
							score++;
						}
						if (base[1]) {
							base[1] = false;
							score++;
						}
						base[3] = true;
					} else if (arr[i][order[idx]] == 4) { // 홈런
						if (base[3] ) {
							base[3] = false;
							score++;
						}
						if (base[2]) {
							base[2] = false;
							score++;
						}
						if (base[1] ) {
							base[1] = false;
							score++;
						}
						score++;
					}
//					System.out.println("타자:" + order[idx]+", 경우:"+arr[i][order[idx]]);
//					System.out.println(Arrays.toString(base)+"점수:"+score);
					idx++;
					if (idx == 10) { // 9번까지 다칠경우 다시 1번이 치기 위해서 초기화
						idx = 1;
					}

				}
//				System.out.println("/////////////////////////////////////////////////////");
			}
//			System.out.println(score);
			MAX = Math.max(MAX, score);
			return;
		}
		for (int i = 1; i < 10; i++) {
			if (visit[i])
				continue;
			if (i == 1 && depth != 4)
				continue;
			if (depth == 4 && i != 1)
				continue;
			visit[i] = true;
			order[depth] = i;
			dfs(depth + 1);
			visit[i] = false;

		}
	}

}
