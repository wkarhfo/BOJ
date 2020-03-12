import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_차이를최대로 {
	static int[] arr;
	static boolean[] visit;
	static int[] result;
	static int MAX;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[N];
		visit = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		MAX = Integer.MIN_VALUE;
		dfs(0);
		System.out.println(MAX);
	}

	private static void dfs(int depth) {
		if (depth == arr.length) {
			int count=0;
			int sum=0;
			while(true) {
				if(count+1==arr.length)
					break;
				sum+=Math.abs(result[count]-result[count+1]);
				count++;
			}
			MAX=Math.max(MAX,sum);
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!visit[i]) {
				result[depth] = arr[i];
				visit[i] = true;
				dfs(depth + 1);
				visit[i] = false;
			}
		}

	}

}
