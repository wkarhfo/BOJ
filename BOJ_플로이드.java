import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_플로이드 {
	static int n, m;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		n = Integer.parseInt(br.readLine().trim());
		m = Integer.parseInt(br.readLine().trim());
		arr = new int[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (arr[a][b] == 0) {
				arr[a][b] = c;
			} else {
				if (arr[a][b] < c) {
					continue;
				} else {
					arr[a][b] = c;
				}
			}
		}

		for (int k = 1; k < n + 1; k++) {
			for (int i = 1; i < arr.length; i++) {
				for (int j = 1; j < arr.length; j++) {
					if(i==j)
						continue;
					if(arr[i][k]==0||arr[k][j]==0)
						continue;
					if(arr[i][j]==0&&arr[i][k]+arr[k][j]>arr[i][j]) {
						arr[i][j]=arr[i][k]+arr[k][j];
					}
					else if (arr[i][k] + arr[k][j] < arr[i][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr.length; j++) {
				sb.append(arr[i][j]).append(" ");
//				System.out.print(arr[i][j] + " ");
			}
			sb.append("\n");
//			System.out.println();
		}
		System.out.println(sb);
	}

}
