import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_이모티콘 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine().trim());

		Queue<Data> q = new LinkedList<>();
		boolean[][] visit = new boolean[S + 1][S + 1];

		q.add(new Data(1, 0, 0));
		visit[1][0] = true;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			if (tmp.a == S) {
				System.out.println(tmp.cnt);
				return;
			}

			if (tmp.a != 0 && visit[tmp.a][tmp.a] == false) {
				visit[tmp.a][tmp.a] = true;
				q.add(new Data(tmp.a, tmp.a, tmp.cnt + 1));
			}

			if (tmp.b != 0 &&tmp.a + tmp.b<visit.length &&visit[tmp.a + tmp.b][tmp.b] == false)

			{
				visit[tmp.a + tmp.b][tmp.b] = true;
				q.add(new Data(tmp.a + tmp.b, tmp.b, tmp.cnt + 1));
			}

			int temp = tmp.a - 1;

			if (tmp.a != 0 && visit[temp][tmp.b] == false)

			{
				visit[temp][tmp.b] = true;
				q.add(new Data(temp, tmp.b, tmp.cnt + 1));
			}

		}

	}

	static class Data {
		int a;
		int b;
		int cnt;

		public Data(int a, int b, int cnt) {
			super();
			this.a = a;
			this.b = b;
			this.cnt = cnt;
		}
	}

}
