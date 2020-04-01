import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_스타트링크 {
	static int F,S,G,U,D;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		F=Integer.parseInt(st.nextToken()); //총크기
		S=Integer.parseInt(st.nextToken()); //현재 위치
		G=Integer.parseInt(st.nextToken()); //도착점
		U=Integer.parseInt(st.nextToken()); // 위
		D=Integer.parseInt(st.nextToken()); //아래
		visit=new boolean[F+1];
		
		Queue<Data> q=new LinkedList<>();
		q.add(new Data(S,0));
		visit[S]=true;
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			if(tmp.idx==G) {
				System.out.println(tmp.cnt);
				return;
			}
			for(int k=0;k<2;k++) {
				int ah=tmp.idx;
				if(k==0) {
					ah+=U;
				}else {
					ah-=D;
				}
				if(ah<=0||ah>F||visit[ah])
					continue;
				visit[ah]=true;
				q.add(new Data(ah,tmp.cnt+1));
			}
		}
		System.out.println("use the stairs");
	}
	static class Data{
		int idx;
		int cnt;
		public Data(int idx, int cnt) {
			super();
			this.idx = idx;
			this.cnt = cnt;
		}
	}

}
