import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_네트워크복구 {
	static int N, M;
	static ArrayList<Data>[] list;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N + 1][2];
		list = new ArrayList[N + 1];
		for (int i = 1; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			list[A].add(new Data(B, C));
			list[B].add(new Data(A, C));
		}
		for (int i = 0; i < dist.length; i++) {
			dist[i][0] = Integer.MAX_VALUE;
		}
		dijkstra(1);
		int count=0;
		for(int i=2;i<dist.length;i++) {
			if (dist[i][0] != Integer.MAX_VALUE) {
				count++;
			}
		}
		sb.append(count).append("\n");
		for (int i = 2; i < dist.length; i++) {
			if (dist[i][0] != Integer.MAX_VALUE) {
//				System.out.println(i + " " + dist[i][1]);
				sb.append(i).append(" ").append(dist[i][1]).append("\n");
			}
		}
		System.out.println(sb);
	}

	static void dijkstra(int num) {
		PriorityQueue<Data> q = new PriorityQueue<>();
		q.add(new Data(num, 0));
		dist[num][0] = 0;
		boolean[] visit = new boolean[N + 1];
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			int cur = tmp.dest;
			if (visit[cur])
				continue;

			visit[cur] = true;
			for (Data data : list[cur]) {
				if (dist[data.dest][0] > dist[cur][0] + data.cnt) {
					dist[data.dest][0] = dist[cur][0] + data.cnt;
					dist[data.dest][1] = cur;
					q.add(new Data(data.dest, dist[data.dest][0]));
				}
			}
		}

	}

	static class Data implements Comparable<Data> {
		int dest;
		int cnt;

		public Data(int dest, int cnt) {
			super();
			this.dest = dest;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Data o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cnt, o.cnt);
		}

	}
}
