import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_숨바꼭질2 {
	static int N, K;
	static int[] dx = { -1, 1, 2 };
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new boolean[100000 + 1];
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(N, 0));
		//visit[N] = true;
		int count = 0;
		int min = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Data temp = q.poll();
			if(temp.x!=K)
				visit[temp.x] = true;
			
			if (temp.x == K &&temp.cnt<=min) {
				
				min = temp.cnt; count++;
			}
			
			  if (min < temp.cnt) continue;
			 
			
			for (int k = 0; k < 3; k++) {
				
				int ax = 0;
				if (k == 0) {
					ax = temp.x + dx[k];
				} else if (k == 1) {
					ax = temp.x + dx[k];
				} else {
					ax = temp.x * dx[k];
				}
				if (ax < 0 || ax >= 100001 ||visit[ax] == true)
					continue;
				q.add(new Data(ax, temp.cnt + 1));	
			}

		}
		System.out.println(min);
		System.out.println(count);
	}

	static class Data {
		int x;
		int cnt;

		public Data(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}

	}
}
