import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_가족관계 {
	static int[][] arr;
	static int findX;
	static int findY;
	static boolean[][] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int people = sc.nextInt();
		findX = sc.nextInt();
		findY = sc.nextInt();
		int order = sc.nextInt();
		arr = new int[people + 1][people + 1];
		visit=new boolean[people+1][people+1];
		for (int i = 0; i < order; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arr[x][y] = arr[y][x] = 1;
		}
		bfs(findY);
		

	}
	private static void bfs(int findY) {
		Queue<Data> q=new LinkedList<>();
		for(int i=1;i<arr.length;i++) {
			if(arr[i][findY]==1) {
				Data tmp=new Data(i,1);
				q.add(tmp);
				visit[i][findY]=true;
				visit[findY][i]=true;
			}
		}
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			if(tmp.idx==findX) {
				System.out.println(tmp.cnt);
				return;
			}
			for(int i=1;i<arr.length;i++) {
				if(arr[i][tmp.idx]==1&&visit[i][tmp.idx]==false) {
					Data newData=new Data(i,tmp.cnt+1);
					visit[i][tmp.idx]=true;
					visit[tmp.idx][i]=true;
					q.add(newData);
				}
					
			}
		}
		System.out.println(-1);
	}
	static class Data{
		int idx;
		int cnt;
		public Data(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
		
	}
}
