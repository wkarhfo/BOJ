import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_카잉달력 {
	static int M, N, x, y;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < TC; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;

			int a = x;
			boolean flag = false;
			while (a<M*N) {
				if (flag=(a % N == y)) {
					System.out.println(a+1);
					break;
				}
				a += M;
			}
			
			if(!flag) {
				System.out.println(-1);
			}
		

		}
	}

}
