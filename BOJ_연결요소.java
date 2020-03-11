import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_연결요소 {
	static int[][] arr;
	static int count;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int order = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		visit=new boolean[N+1];
		for (int i = 0; i < order; i++) {
			st = new StringTokenizer(br.readLine());
			int hang = Integer.parseInt(st.nextToken());
			int yeul = Integer.parseInt(st.nextToken());
			arr[hang][yeul] = 1;
			arr[yeul][hang] = 1;
		}
		
		count=0;
		for (int i = 1; i <= N; i++) {
			bfs(i);
		}
		for(int i=1;i<=N;i++) {
			if(!visit[i]) {
				count++;
			}
		}
		System.out.println(count);
	}

	private static void bfs(int y) {
		Queue<Data> q = new LinkedList<>();
		boolean flag=false;
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i][y] == 1) {
				q.add(new Data(i, y));
				arr[i][y] = 0;
				arr[y][i] = 0;
				visit[i]=true;
				flag=true;
			}
		}
		while (!q.isEmpty()) {
			Data tmp = q.poll();
			for (int i = 1; i < arr.length; i++) {
				if (arr[i][tmp.h] == 1) {
					q.add(new Data(i,tmp.h));
					arr[i][tmp.h]=0;
					arr[tmp.h][i]=0;
					visit[i]=true;
				}
			}
			
		}
		if(flag) {
			visit[y]=true;
			count++;
		}
	}
	
	static class Data {
		int h;
		int y;

		public Data(int h, int y) {
			this.h = h;
			this.y = y;
		}

	}
}