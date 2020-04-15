import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_도시분할계획 {
	static int N, M, A, B, C;
	static ArrayList<Data>[] list;
	static boolean[] visit;
	static int result=0;// 최소합
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		visit = new boolean[N + 1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			list[A].add(new Data(B, C));
			list[B].add(new Data(A, C));
		}
		
		int max=0;
		PriorityQueue<Data> q=new PriorityQueue<>();
		visit[1]=true;
		q.addAll(list[1]);
		int cnt=1;
		while(cnt!=N) {
			Data tmp=q.poll();
			if(visit[tmp.dest])
				continue;
			
			max=Math.max(max, tmp.cnt);
			visit[tmp.dest]=true;
			q.addAll(list[tmp.dest]);
			result+=tmp.cnt;
			cnt++;
		}
		System.out.println(result-max);
	}

	static class Data implements Comparable<Data>{
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
