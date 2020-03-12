import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_퇴사 {
	static int[][] arr;
	static int N;
	static int MAX;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][2];
		for (int i = 1; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		MAX=0;
		for (int i = 1; i < arr.length; i++) {
			if (i + arr[i][0]-1 <=N)
				bfs(i);
		}
		System.out.println(MAX);
	}

	private static void bfs(int idx) {
		Queue<Data> q=new LinkedList<>();
		q.add(new Data(idx,arr[idx][1]));
		
		while(!q.isEmpty()) {
			Data temp=q.poll();
			MAX=Math.max(MAX, temp.sum);
			for(int i=arr[temp.idx][0]+temp.idx;i<arr.length;i++) {
				if(i+arr[i][0]-1<=N) {
					q.add(new Data(i,temp.sum+arr[i][1]));
				}
			}
		}
		
	}
	static class Data{
		int idx;
		int sum;
		public Data(int idx, int sum) {
			this.idx = idx;
			this.sum = sum;
		}
		
	}

}
