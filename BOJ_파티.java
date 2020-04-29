import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_파티 {
	static int N, M, X;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[a][b] = c;
		}
	
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (arr[i][k] == 0 || arr[k][j] == 0)
						continue;
					if (arr[i][k] + arr[k][j] < arr[i][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
					if(arr[i][j]==0&&arr[i][k]+arr[k][j]>0)
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		int max = 0;
		for(int i=1;i<=N;i++) {
			if(i==X)
				continue;
			if(arr[i][X]+arr[X][i]>max)
				max=arr[i][X]+arr[X][i];
		}
		System.out.println(max);
	}
}
