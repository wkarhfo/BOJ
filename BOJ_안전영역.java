import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_안전영역 {
	static int[][] arr;
	static ArrayList<Data> list;
	static Data[] result;
	static boolean[][] visit;
	static int[] dh= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int[][] temp;
	static int last;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int hang=sc.nextInt();
		int yeul=sc.nextInt();
		arr=new int[hang][yeul];
		list =new ArrayList<>();
		result=new Data[3];
		for(int i=0;i<hang;i++) {
			for(int j=0;j<yeul;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		for(int i=0;i<hang;i++) {
			for(int j=0;j<yeul;j++) {
				if(arr[i][j]==0) {
					Data tmp=new Data(i,j);
					list.add(tmp);
				}
			}
		}
		last=Integer.MIN_VALUE;
		dfs(0,0);
		System.out.println(last);
		
	}
	static void dfs(int begin,int depth) {
		if(depth==3) {
			/*
			 * for(int i=0;i<result.length;i++) {
			 * System.out.print("("+result[i].h+","+result[i].y+")"); }
			 */
			temp=new int[arr.length][arr[0].length];
			visit=new boolean[arr.length][arr[0].length];
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr[0].length;j++) {
					temp[i][j]=arr[i][j];
				}
			}
			
			for(int i=0;i<result.length;i++) {
				temp[result[i].h][result[i].y]=1;
			}
			for(int i=0;i<temp.length;i++) {
				for(int j=0;j<temp[0].length;j++) {
					if(temp[i][j]==2&&visit[i][j]==false) {
						bfs(i,j);
					}
				}
			}
			int count=0;
			for(int i=0;i<temp.length;i++) {
				for(int j=0;j<temp[0].length;j++) {
					if(temp[i][j]==0)
						count++;
				}
			}
			if(count>last) {
				last=count;
			}
//			System.out.println(count);
			return;
		}
		for(int i=begin;i<list.size();i++) {
			result[depth]=list.get(i);
			dfs(i+1,depth+1);
		}
	}
	 static void bfs(int i, int j) {
		Queue<Data> q=new LinkedList<>();
		Data start=new Data(i,j);
		visit[i][j]=true;
		q.add(start);
		while(!q.isEmpty()) {
			Data tmp=q.poll();
			for(int k=0;k<4;k++) {
				int ah=tmp.h+dh[k];
				int ay=tmp.y+dy[k];
				if(ah<0||ah>=temp.length||ay<0||ay>=temp[0].length||visit[ah][ay]==true||temp[ah][ay]==1) {
					continue;
				}
				temp[ah][ay]=2;
				Data newData=new Data(ah,ay);
				q.add(newData);
				visit[ah][ay]=true;
			}
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
