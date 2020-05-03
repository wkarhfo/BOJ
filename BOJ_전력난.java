import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_전력난 {
	static int m, n;
	static int a, b, c;
	static ArrayList<Data>[] list;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if (m == 0 && n == 0)
				break;
			
			sum=0;
			list = new ArrayList[m];
			for (int i = 0; i < m; i++) {
				list[i] = new ArrayList<>();
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				list[a].add(new Data(b, c));
				list[b].add(new Data(a, c));
				sum += c;

			}

			boolean[] visit = new boolean[m];
			visit[0] = true;
			PriorityQueue<Data> q = new PriorityQueue<Data>();
			q.addAll(list[0]);
			int count = 1;
			int result = 0;
			while (count != m) {
				Data tmp = q.poll();
				if (visit[tmp.dest])
					continue;

				visit[tmp.dest] = true;
				q.addAll(list[tmp.dest]);
				result += tmp.weight;
				count++;
			}
			System.out.println(sum - result);

		}
	}

	static class Data implements Comparable<Data> {
		int dest;
		int weight;

		public Data(int dest, int weight) {
			super();
			this.dest = dest;
			this.weight = weight;
		}

		@Override
		public int compareTo(Data o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
