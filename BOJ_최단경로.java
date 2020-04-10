import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ_최단경로 {
	static int V, E, K;
	static ArrayList<Data>[] list;
	private static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine().trim());
		list = new ArrayList[V + 1];
		dist = new int[V + 1];
		
		for (int i = 1; i < V + 1; i++) {
			list[i] = new ArrayList<>();
		}
		Arrays.fill(dist, Integer.MAX_VALUE);

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Data(v, w));
		}
		dijkstra(K);
		for(int i=1;i<V+1;i++) {
			if(i==K) {
				sb.append(0).append("\n");
//				System.out.println(0);
			}else if(dist[i]==Integer.MAX_VALUE){
				sb.append("INF").append("\n");
//				System.out.println("INF");
			}else {
				sb.append(dist[i]).append("\n");
//				System.out.println(dist[i]);
			}
		}
		System.out.println(sb);
	}

	static void dijkstra(int st) {
		PriorityQueue<Data> q = new PriorityQueue<>();
		boolean[] visit = new boolean[V + 1];
		q.add(new Data(st, 0));
		dist[st] = 0;
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			int cur = tmp.v;
			if (visit[cur])
				continue;

			visit[cur] = true;
			for (Data data : list[cur]) {
				if(dist[data.v]>dist[cur]+data.cnt) {
					dist[data.v]=dist[cur]+data.cnt;
					q.add(new Data(data.v,dist[data.v]));
				}
			}
		}
	}

	static class Data implements Comparable<Data> {
		@Override
		public int compareTo(Data o) {
			return Integer.compare(this.cnt, o.cnt);
		}

		int v;
		int cnt;

		public Data(int v, int cnt) {
			super();
			this.v = v;
			this.cnt = cnt;
		}

	}
}
