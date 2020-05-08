import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_게리맨더링2 {
	static int N;
	static int[][] arr;
	static int[][] map;// 선거구 나누는 이차원 배열
	static int[] temp;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int lastResult = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		temp = new int[4];
		dfs(0);
		System.out.println(lastResult);
	}

	static void dfs(int depth) {
		if (depth == 4) {
			map = new int[N + 1][N + 1];
			// 1.기준점과 경계의 길이 정하기
			if (temp[0] + temp[2] + temp[3] > N) // 1 ≤ x < x+d1+d2 ≤ N
				return;
			if (temp[1] - temp[2] < 1 || temp[1] + temp[3] > N) // 1 ≤ y-d1 < y < y+d2 ≤ N
				return;

			// 2. 경계선 만들기
			int tmp = 0;
			while (true) { // (x, y), (x+1, y-1), ..., (x+d1, y-d1)
				if (tmp > temp[2])
					break;
				map[temp[0] + tmp][temp[1] - tmp] = 5;
				tmp++;
			}
			tmp = 0;
			while (true) { // (x, y), (x+1, y+1), ..., (x+d2, y+d2)
				if (tmp > temp[3])
					break;
				map[temp[0] + tmp][temp[1] + tmp] = 5;
				tmp++;
			}
			int ah = temp[0] + temp[2];
			int ay = temp[1] - temp[2];
			tmp = 0;
			while (true) { // (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
				if (tmp > temp[3])
					break;
				map[ah + tmp][ay + tmp] = 5;
				tmp++;
			}

			ah = temp[0] + temp[3];
			ay = temp[1] + temp[3];
			tmp = 0;
			while (true) { // (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
				if (tmp > temp[2])
					break;
				map[ah + tmp][ay - tmp] = 5;
				tmp++;
			}

			// 3. 경계선과 경계선안에 포한된 구역 5번선거구 만들기
			for (int i = 1; i < map.length; i++) {
				for (int j = 1; j < map.length; j++) {
					int count = 0;
					for (int k = 0; k < 4; k++) {
						int th = i + dh[k];
						int ty = j + dy[k];
						if (th < 1 || th >= map.length || ty < 1 || ty >= map[0].length)
							continue;
						if (map[th][ty] == 5)
							count++;
					}
					if (count >= 3) {
						five(i, j);
					}
				}
			}
			// 4. 다른 선거구 번호 입력하기
			for (int i = 1; i < temp[0] + temp[2]; i++) { // 1번 선거구
				for (int j = 1; j <= temp[1]; j++) {
					if (map[i][j] == 0) {
						map[i][j] = 1;
					}
				}
			}
			for (int i = 1; i <= temp[0] + temp[3]; i++) { // 2번 선거구
				for (int j = temp[1] + 1; j <= N; j++) {
					if (map[i][j] == 0) {
						map[i][j] = 2;
					}
				}
			}
			for (int i = temp[0] + temp[2]; i <= N; i++) { // 3번 선거구
				for (int j = 1; j < temp[1] - temp[2] + temp[3]; j++) {
					if (map[i][j] == 0) {
						map[i][j] = 3;
					}
				}
			}
			for (int i = temp[0] + temp[3] + 1; i <= N; i++) { // 4번 선거구
				for (int j = temp[1] - temp[2] + temp[3]; j <= N; j++) {
					if (map[i][j] == 0) {
						map[i][j] = 4;
					}
				}
			}
			// 출력
//			for (int i = 1; i < map.length; i++) {
//				for (int j = 1; j < map.length; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println(Arrays.toString(temp));

			int[] result = new int[5 + 1];
			// 인구 수 많은 곳과 적은곳의 차이 값
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					result[map[i][j]] += arr[i][j];
				}
			}
			Arrays.sort(result);
//			System.out.println(Arrays.toString(result));
//			System.out.println(result[result.length - 1] - result[1]);
			lastResult=Math.min(lastResult, Math.abs(result[result.length - 1] - result[1]));
			return;
		}
		for (int i = 1; i < N; i++) {
			temp[depth] = i;
			dfs(depth + 1);
		}
	}

	static void five(int h, int y) {
		map[h][y] = 5;
		for (int k = 0; k < 4; k++) {
			int ah = h + dh[k];
			int ay = y + dy[k];
			if (map[ah][ay] == 5)
				continue;
			five(ah, ay);
		}
	}
}
