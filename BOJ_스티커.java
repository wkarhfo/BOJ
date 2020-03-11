import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_스티커 {
	static int result;
	static int[][] arr;
	static boolean[][] visit;
	static int[] dh= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int TC=Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			int y=Integer.parseInt(br.readLine());
			arr=new int[2][y];
			visit=new boolean[2][y];
			
			for(int i=0;i<2;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<y;j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr[0].length;j++) {					
					dfs(i,j);
				}
			}
			
			System.out.println(result);
		}
	}
	private static void dfs(int h, int y) {
		
		visit[h][y]=true;
		for(int k=0;k<4;k++) {
			int ah=h+dh[k];
			int ay=y+dy[k];
			if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length||visit[ah][ay]==true)
				continue;
			visit[ah][ay]=true;
		}
		
	}
}
