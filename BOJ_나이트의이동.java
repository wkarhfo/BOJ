import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_나이트의이동 {
	static int[] dh= {-2,-1,1,2,2,1,-1,-2};
	static int[] dy= {1,2,2,1,-1,-2,-2,-1};
	static int[][] arr;
	static boolean[][] visit;
	static int startH;
	static int startY;
	static int endH;
	static int endY;
	static int result;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int test=sc.nextInt();
		for(int tc=1;tc<=test;tc++) {
			int size=sc.nextInt();
			arr=new int[size][size];
			visit=new boolean[size][size];
			startH=sc.nextInt();
			startY=sc.nextInt();
			endH=sc.nextInt();
			endY=sc.nextInt();
			
			result=0;
			bfs(startH,startY);
			System.out.println(result);
		}
	}
	static void bfs(int startH, int startY) {
		Queue<Data> q=new LinkedList<>();
		Data start=new Data(startH,startY,1);
		q.add(start);
		visit[startH][startY]=true;
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			for(int k=0;k<8;k++) {
				int ah=tmp.h+dh[k];
				int ay=tmp.y+dy[k];
				
				if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay]==true)
					continue;
				visit[ah][ay]=true;
				Data newData=new Data(ah,ay,tmp.count+1);
				q.add(newData);
				if(ah==endH&&ay==endY) {
					result=tmp.count;
					break;
				}
			}
		}
		
		
	}
	static class Data{
		int h;
		int y;
		int count;
		public Data(int h, int y,int count) {
			this.h = h;
			this.y = y;
			this.count=count;
		}
		
	}
}
