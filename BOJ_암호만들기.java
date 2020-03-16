import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_암호만들기 {
	static int L, C;
	static boolean[] visit;
	static char[] arr;
	static char[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visit = new boolean[C];
		arr = new char[C];
		result = new char[L];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		dfs(0, ' ');
	}

	private static void dfs(int depth,char pre) {
		if (depth == L) {
			int countA=0;
			int countB=0;
			for(int i=0;i<result.length;i++) {
				if(result[i]=='a'||result[i]=='e'||result[i]=='i'||result[i]=='o'||result[i]=='u') {
					countA++;
				}else {
					countB++;
				}
			}
			if(countA<1) {
				return;
			}
			if(countB<2) {
				return;
			}
			 
			for(int i=0;i<result.length;i++) {
				System.out.print(result[i]);
			}
			System.out.println();
			return;

		}
		for (int i = 0; i < C; i++) {
			if (visit[i])
				continue;
			
			if(pre-'a'>arr[i]-'a')
				continue;
			
			visit[i] = true;
			result[depth] = arr[i];
			dfs(depth + 1,arr[i]);
			visit[i] = false;

		}
	}
}
