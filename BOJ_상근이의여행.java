import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_상근이의여행 {
	static int N;
	static int M;
	static int[][] arr;
	static boolean[][] visit;
	static int[] result;
	static int sum;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int test=sc.nextInt();
		for(int tc=1;tc<=test;tc++) {
			N=sc.nextInt();
			M=sc.nextInt();
			arr=new int[N+1][N+1];
			visit=new boolean[N+1][N+1];
			result=new int[N+1];
			for(int i=0;i<M;i++) {
				int a=sc.nextInt();
				int b=sc.nextInt();
				arr[a][b]=1;
				arr[b][a]=1;
			}
			sum=0;
			bfs(1);
			int sum=0;
			for(int i=1;i<result.length;i++) {
				sum+=result[i];
			}
			System.out.println(sum);
		}
	}
	static void bfs(int start) {
		Queue<Data> q=new LinkedList<>();
		for(int i=1;i<arr.length;i++) {
			if(arr[i][start]==1) {
				visit[i][start]=true;
				visit[start][i]=true;
				Data tmp=new Data(i,1);
				q.add(tmp);
			}
		}
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			for(int i=1;i<=N;i++) {
				if(tmp.idx==i) {
					if(result[i]==0) {
						result[i]=tmp.cnt;
					}
				}
			}
			for(int i=1;i<arr.length;i++) {
				if(arr[i][tmp.idx]==0||visit[i][tmp.idx]==true)
					continue;
				sum=tmp.cnt;
				Data newData=new Data(i,1);
				visit[i][tmp.idx]=true;
				visit[tmp.idx][i]=true;
				q.add(newData);
				
			}
		}
		
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
