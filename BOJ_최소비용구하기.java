import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_최소비용구하기 {
	static int N, M, a, b, c;
	static ArrayList<Data>[] list;
	static int K, F;
	static int[] dist;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		list = new ArrayList[N + 1];
		dist = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list[a].add(new Data(b, c));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		Arrays.fill(dist, Integer.MAX_VALUE);
		dijsktra(K);
		System.out.println(dist[F]);
	}

	private static void dijsktra(int num) {
		PriorityQueue<Data> q = new PriorityQueue<>();
		q.add(new Data(num, 0));
		dist[num]=0;
		boolean[] visit=new boolean[N+1];
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			int cur=tmp.dest;
			if(visit[cur])
				continue;
			visit[cur]=true;
			for(Data data:list[cur]) {
				if(dist[data.dest]>dist[cur]+data.cnt) {
					dist[data.dest]=dist[cur]+data.cnt;
					q.add(new Data(data.dest,dist[data.dest]));
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
