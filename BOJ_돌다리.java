import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_돌다리 {
	static int powerA;
	static int powerB;
	static int idx;
	static int result;
	static boolean[] visit;
//	static int[] dx={powerA,powerB,powerA,powerB};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		powerA=sc.nextInt();
		powerB=sc.nextInt();
		idx=sc.nextInt();
		result=sc.nextInt();
		visit=new boolean[100000+1];
		bfs(idx);
	}
	static void bfs(int idx) {
		Queue<Data> q=new LinkedList<>();
		Data start=new Data(idx,0);
		q.add(start);
		visit[idx]=true;
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			if(tmp.idx==result) {
				System.out.println(tmp.cnt);
				break;
			}
			for(int k=0;k<10;k++) {
				int temp=tmp.idx;
				if(k==0) {
					temp+=powerA;
				}else if(k==1) {
					temp-=powerA;
				}else if(k==2) {
					temp*=powerA;
				}else if(k==3) {
					temp*=-powerA;
				}else if(k==4) {
					temp+=powerB;
				}else if(k==5) {
					temp-=powerB;
				}else if(k==6) {
					temp*=powerB;
				}else if(k==7) {
					temp*=-powerB;
				}else if(k==8) {
					temp+=1;
				}else if(k==9) {
					temp+=-1;
				}
				if(temp<0||temp>100000||visit[temp]==true)
					continue;
				Data newData=new Data(temp,tmp.cnt+1);
				visit[temp]=true;
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
