import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_월드컵 {
	static int[] team1 = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	static int[] team2 = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };
	static int[][] arr;
	static int[][] temp;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[6][3];
		temp = new int[6][3];
		result = new int[4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(i, 0);
		}
		for(int i=0;i<result.length;i++)
			System.out.print(result[i]+" ");
	}

	static void dfs(int idx, int cnt) {
		if (cnt == 15) {
			if (result[idx] != 1) {
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 3; j++) {
						if (arr[i][j] != temp[i][j])
							return;
					}
				}
				result[idx] = 1;
				return;

			} else
				return;
		}

		int t1 = team1[cnt];
		int t2 = team2[cnt];
		// t1승 t2패
		temp[t1][0]++;
		temp[t2][2]++;
		dfs(idx, cnt + 1);
		temp[t1][0]--;
		temp[t2][2]--;
		// 무승부
		temp[t1][1]++;
		temp[t2][1]++;
		dfs(idx, cnt + 1);
		temp[t1][1]--;
		temp[t2][1]--;
		// t1패 t2승
		temp[t1][2]++;
		temp[t2][0]++;
		dfs(idx, cnt + 1);
		temp[t1][2]--;
		temp[t2][0]--;
	}

}
