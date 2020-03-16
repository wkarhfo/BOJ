import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_스타트와링크 {
	static int N;
	static boolean[] visit;
	static int[][] arr;
	static int MIN=Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine().trim());
		arr=new int[N][N];
		visit=new boolean[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0);
		System.out.println(MIN);
		
	}
	private static void dfs(int start, int depth) {
		if(depth==N/2) {
			ArrayList<Integer> a=new ArrayList<>();
			ArrayList<Integer> b=new ArrayList<>();
			for(int i=0;i<visit.length;i++) {
				if(visit[i]) {
					a.add(i);
				}else {
					b.add(i);
				}
			}
			
			int sumA=0;
			int sumB=0;
			for(int i=0;i<a.size()-1;i++) {
				for(int j=i;j<a.size();j++) {
					sumA+=arr[a.get(i)][a.get(j)]+arr[a.get(j)][a.get(i)];
					sumB+=arr[b.get(i)][b.get(j)]+arr[b.get(j)][b.get(i)];
				}
			}
			int tmp=Math.abs(sumA-sumB);
			MIN=Math.min(MIN, tmp);
			return;
			
		}
		for(int i=start;i<N;i++) {
			visit[i]=true;
			dfs(i+1,depth+1);
			visit[i]=false;
		}
	}
}
