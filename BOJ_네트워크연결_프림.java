import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_네트워크연결_프림 {
	static int N, M, a, b, c;
	static ArrayList<Data>[] list;
	static boolean[] visit;
	static int result=0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		list = new ArrayList[N + 1];
		visit = new boolean[N + 1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list[a].add(new Data(b, c));
			list[b].add(new Data(a, c));
		}
		
		PriorityQueue<Data> q=new PriorityQueue<Data>();
		visit[1]=true;
		q.addAll(list[1]);
		int cnt=1;
		while(cnt!=N) {
			Data tmp=q.poll();
			if(visit[tmp.end])
				continue;
			
			result+=tmp.count;
			visit[tmp.end]=true;
			q.addAll(list[tmp.end]);
			cnt++;
		}
		System.out.println(result);
	}

	static class Data implements Comparable<Data>{
		int end;
		int count;

		public Data( int end, int count) {
			super();
			this.end = end;
			this.count = count;
		}

		@Override
		public int compareTo(Data o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.count, o.count);
		}
	}
}
