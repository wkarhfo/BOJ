import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_숨바꼭질4 {
	static int N, K;
	static int[] dx = { -1, 1, 2 };
	static int[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visit = new int[100000 + 1];
		for(int i=0;i<visit.length;i++) {
			visit[i]=-1;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(N);
		visit[N] = -2;
		while (!q.isEmpty()) {
			int temp = q.poll();
			if(temp==K) {
				ArrayList<Integer> list=new ArrayList<>();
				int pos=temp;
				while(pos!=-2) {
					list.add(pos);
					pos=visit[pos];
				}
				
				System.out.println(list.size()-1);
				for(int i=list.size()-1;i>=0;i--) {
					sb.append(list.get(i)).append(" ");
				}
				System.out.println(sb);
				return;
			}
			for (int k = 0; k < 3; k++) {
				int ax = 0;
				if (k == 0 || k == 1) {
					ax = temp + dx[k];
				} else {
					ax = temp * dx[k];
				}
				if (ax < 0 || ax >= 100001)
					continue;
				if(visit[ax]!=-1||visit[ax]==-2)
					continue;
				visit[ax]=temp;
				q.add(ax);	
				
			}
		}

	}

}
