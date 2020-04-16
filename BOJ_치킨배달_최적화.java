import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 *	치킨집의 위치리스트를 만든후
 *	M개를 뽑는 조합...
 *	사람의 위치리스트를 만든후
 *	사람의위치당 각 치킨집에까지의 거리에서의 최소값을 정한뒤
 *	그값을 더해간다. 
 * 
 *
 */
public class BOJ_치킨배달_최적화{
	static int N, M;
	static int[][] arr;
	static ArrayList<Data> list;
	static ArrayList<Data> peoplelist;
	static boolean[] visit;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int result;
	static int lastMIN=Integer.MAX_VALUE;
	static int MIN;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		list = new ArrayList<>();
		peoplelist=new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					list.add(new Data(i, j));
				}else if(arr[i][j]==1) {
					peoplelist.add(new Data(i,j));
				}
			}
		}
		visit = new boolean[list.size()];
		dfs(0, 0);
		System.out.println(lastMIN);

	}

	static void dfs(int start, int depth) {
		if (depth == M) {
			result=0;
			for(int i=0;i<peoplelist.size();i++) {
				MIN=Integer.MAX_VALUE;
				for(int j=0;j<visit.length;j++) {
					if(visit[j]) {
						int temp=Math.abs(peoplelist.get(i).h-list.get(j).h)+Math.abs(peoplelist.get(i).y-list.get(j).y);
						MIN=Math.min(MIN, temp);
					}
				}
				result+=MIN;
			}
			lastMIN=Math.min(result, lastMIN);
		}
		for (int i = start; i < list.size(); i++) {
			visit[i] = true;
			dfs(i + 1, depth + 1);
			visit[i] = false;
		}
	}

	static class Data {
		int h;
		int y;
	

		public Data(int h, int y) {
			super();
			this.h = h;
			this.y = y;
		}
	}
}
