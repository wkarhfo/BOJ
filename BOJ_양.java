import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_양 {
	static char[][] arr;
	static boolean[][] visit;
	static int[] dh= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int sheep;
	static int wolf;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int hang=sc.nextInt();
		int yeul=sc.nextInt();
		arr=new char[hang][yeul];
		visit=new boolean[hang][yeul];
		
		for(int i=0;i<hang;i++) {
			arr[i]=sc.next().toCharArray();
		}
		sheep=0;
		wolf=0;
		for(int i=0;i<hang;i++) {
			for(int j=0;j<yeul;j++) {
				if(arr[i][j]!='#'&&visit[i][j]==false) {
					bfs(i,j);
				}
			}
		}
		System.out.println(sheep+" "+wolf);
		
	}
	private static void bfs(int i, int j) {
		Queue<Data> q=new LinkedList<>();
		Data start=new Data(i,j);
		q.add(start);
		visit[i][j]=true;
		int sheepcount=0;
		int wolfcount=0;
		if(arr[i][j]=='v') {
			wolfcount++;
		}else if(arr[i][j]=='o') {
			sheepcount++;
		}
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			for(int k=0;k<4;k++) {
				int ah=tmp.h+dh[k];
				int ay=tmp.y+dy[k];
				if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay]==true||arr[ah][ay]=='#') {
					continue;
				}
				if(arr[ah][ay]=='v') {
					wolfcount++;
				}
				if(arr[ah][ay]=='o') {
					sheepcount++;
				}
				Data newdata=new Data(ah,ay);
				visit[ah][ay]=true;
				q.add(newdata);
			}
		}
		if(sheepcount>wolfcount) {
			sheep+=sheepcount;
		}else {
			wolf+=wolfcount;
		}
		
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
