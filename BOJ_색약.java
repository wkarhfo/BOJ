import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_색약 {
	static char[][] arr;
	static boolean[][] visit;
	static int[] dh= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int nonColor;
	static int color;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		arr=new char[N][N];
		visit=new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			arr[i]=sc.next().toCharArray();
		}
		//색약 아닌사람
		nonColor=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if (visit[i][j]==false) {
					bfs(i,j);
				}
			}
		}
		color=0;
		visit=new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if (visit[i][j]==false) {
					bfs2(i,j);
				}
			}
		}
		System.out.println(nonColor+" "+color);
	}

	static void bfs2(int i, int j) {
		Queue<Data> q=new LinkedList<>();
		Data start= new Data(i,j);
		q.add(start);
		visit[i][j]=true;
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			if(arr[tmp.h][tmp.y]=='R') {
				for(int k=0;k<4;k++) {
					int ah=tmp.h+dh[k];
					int ay=tmp.y+dy[k];
					if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay]==true||arr[ah][ay]=='B')
						continue;
					Data newData=new Data(ah,ay);
					visit[ah][ay]=true;
					q.add(newData);
				}
			}else if (arr[tmp.h][tmp.y]=='B') {
				for(int k=0;k<4;k++) {
					int ah=tmp.h+dh[k];
					int ay=tmp.y+dy[k];
					if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay]==true||arr[ah][ay]=='R'||arr[ah][ay]=='G')
						continue;
					Data newData=new Data(ah,ay);
					visit[ah][ay]=true;
					q.add(newData);
				}
			}else {
				for(int k=0;k<4;k++) {
					int ah=tmp.h+dh[k];
					int ay=tmp.y+dy[k];
					if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay]==true||arr[ah][ay]=='B')
						continue;
					Data newData=new Data(ah,ay);
					visit[ah][ay]=true;
					q.add(newData);
				}
			}
		}
		color++;
	}

	private static void bfs(int i, int j) {
		Queue<Data> q=new LinkedList<>();
		Data start= new Data(i,j);
		q.add(start);
		visit[i][j]=true;
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			if(arr[tmp.h][tmp.y]=='R') {
				for(int k=0;k<4;k++) {
					int ah=tmp.h+dh[k];
					int ay=tmp.y+dy[k];
					if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay]==true||arr[ah][ay]=='B'||arr[ah][ay]=='G')
						continue;
					Data newData=new Data(ah,ay);
					visit[ah][ay]=true;
					q.add(newData);
				}
			}else if (arr[tmp.h][tmp.y]=='B') {
				for(int k=0;k<4;k++) {
					int ah=tmp.h+dh[k];
					int ay=tmp.y+dy[k];
					if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay]==true||arr[ah][ay]=='R'||arr[ah][ay]=='G')
						continue;
					Data newData=new Data(ah,ay);
					visit[ah][ay]=true;
					q.add(newData);
				}
			}else {
				for(int k=0;k<4;k++) {
					int ah=tmp.h+dh[k];
					int ay=tmp.y+dy[k];
					if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay]==true||arr[ah][ay]=='R'||arr[ah][ay]=='B')
						continue;
					Data newData=new Data(ah,ay);
					visit[ah][ay]=true;
					q.add(newData);
				}
			}
		}
		nonColor++;
	}
	static class Data{
		int h;
		int y;
		public Data(int h, int y) {
			this.h = h;
			this.y = y;
		}
		
	}
}
