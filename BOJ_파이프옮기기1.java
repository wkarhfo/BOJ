import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_파이프옮기기1 {
	static int[][] arr;
	static int resultH;
	static int resultY;
	static int count = 0;
	static int[] dh = { 0, 1, 1 }; //가로 세로 대각선
	static int[] dy = { 1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine().trim());
		arr = new int[size][size];
		resultH = resultY = size - 1;
		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 1, 0);
		System.out.println(count);
	}

	private static void dfs(int h, int y, int type) {
		if(h==resultH&&y==resultY) {
			count++;
			return;
		}
		for(int k=0;k<3;k++) {
			if(type==0&&k==1) // 가로일 경우 세로로 x
				continue;
			else if(type==1&&k==0) //세로일 경우 가로로 x
				continue;
			if(isPromising(h,y,k)) {
				int ah=h+dh[k];
				int ay=y+dy[k];
				dfs(ah,ay,k);
			}
			
		}
		
	}

	private static boolean isPromising(int h, int y, int way) {
		int ah=h+dh[way];
		int ay=y+dy[way];
		if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length)
			return false;
		if(way==2) {
			if(arr[ah][ay]==1||arr[ah-1][ay]==1||arr[ah][ay-1]==1)
				return false;
		}else {
			if(arr[ah][ay]==1)
				return false;
		}
		return true;
	}
}
