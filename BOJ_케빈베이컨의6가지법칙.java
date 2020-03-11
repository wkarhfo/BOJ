import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_케빈베이컨의6가지법칙 {
	static int N;
	static int[][] arr;
	static boolean[][] visit;
	static int[] result;
	static ArrayList<Integer> list;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		int order=sc.nextInt();
		arr=new int[N+1][N+1];
		list = new ArrayList<>();
		
		for(int i=0;i<order;i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			arr[x][y]=1;
			arr[y][x]=1;
		}
		int min=0;
		for(int i=1;i<=N;i++) {
			visit=new boolean[N+1][N+1];
			result=new int[N+1];
			bfs(i);
			int sum=0;
			for(int j=1;j<result.length;j++) {
				sum+=result[j];
			}
			list.add(sum);
		}
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i)<list.get(min)) {
				min=i;
			}
		}
		System.out.println(min+1);
	}
	static void bfs(int start) {
		Queue<Data> q=new LinkedList<>();
		for(int i=1;i<arr.length;i++) {
			if(arr[i][start]==1) {
				Data st=new Data(i,1);
				q.add(st);
				visit[i][start]=true;
				visit[start][i]=true;
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
				if(visit[i][tmp.idx]==true||arr[i][tmp.idx]==0)
					continue;
				Data newData=new Data(i,tmp.cnt+1);
				q.add(newData);
				visit[i][tmp.idx]=true;
				visit[tmp.idx][i]=true;
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
