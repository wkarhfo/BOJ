import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_녹색옷입은애가젤다지 {
	static int N;
	static int[][] arr;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int count = 1;
		while (true) {
			N = Integer.parseInt(br.readLine().trim());
			if (N == 0) {
				System.out.println(sb);
				return;
			}
			arr = new int[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < arr.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < arr[0].length; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result=0;
			PriorityQueue<Data> q = new PriorityQueue<Data>(new Comparator<Data>() {
				@Override
				public int compare(Data o1, Data o2) {
					// TODO Auto-generated method stub
					return o1.sum - o2.sum;
				}
			});
			q.add(new Data(0, 0, arr[0][0]));
			visit[0][0]=true;
			while(!q.isEmpty()) {
				Data tmp=q.poll();
				if(tmp.h==N-1&&tmp.y==N-1) {
					result=tmp.sum;
					break;
				}
				for(int k=0;k<4;k++) {
					int ah=tmp.h+dh[k];
					int ay=tmp.y+dy[k];
					if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay])
						continue;
					q.add(new Data(ah,ay,tmp.sum+arr[ah][ay]));
					visit[ah][ay]=true;
				}
			}
			sb.append("Problem ").append(count).append(": ").append(result).append("\n");
			count++;
		}
	}
	static class Data {
		int h;
		int y;
		int sum;
		public Data(int h, int y, int sum) {
			super();
			this.h = h;
			this.y = y;
			this.sum = sum;
		}
	}
}
