import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_벽부수고이동하기 {
	static int N, M;
	static int endH, endY;
	static int[][] arr;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M][2];
		endH = N - 1;
		endY = M - 1;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}

		Queue<Data> q = new LinkedList<>();
		q.add(new Data(0,0,1,0));
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			visit[tmp.h][tmp.y][tmp.broke]=true;
			if(tmp.h==endH&&tmp.y==endY) {
				System.out.println(tmp.cnt);
				return;
			}
			for(int k=0;k<4;k++) {
				int ah=tmp.h+dh[k];
				int ay=tmp.y+dy[k];
				
				if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay][tmp.broke]==true)
					continue;
				
				if(arr[ah][ay]==1) {
					if(tmp.broke==0) {
						q.add(new Data(ah,ay,tmp.cnt+1,1));
						visit[ah][ay][1]=true;
						continue;
					}else {
						continue;
					}
				}
				q.add(new Data(ah,ay,tmp.cnt+1,tmp.broke));
				visit[ah][ay][tmp.broke]=true;
				
				
			}	
		}
		System.out.println(-1);
	}

	static class Data {
		int h;
		int y;
		int cnt;
		int broke; //0:사용 안함 1:사용

		public Data(int h, int y, int cnt, int broke) {
			super();
			this.h = h;
			this.y = y;
			this.cnt = cnt;
			this.broke = broke;
		}

	}
}
