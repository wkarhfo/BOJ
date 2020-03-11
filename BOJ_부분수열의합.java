import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//부분 수열의 합이 S가 되는 갯수출력

public class BOJ_부분수열의합 {
	static int S;
	static int[] arr;
	static boolean[] visit;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visit = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		count = 0;
		powerSet(0);
		if(S==0)
			System.out.println(count-1);
		else 
			System.out.println(count);
	}

	private static void powerSet(int depth) {
		if (depth == arr.length) {
			int sum=0;
			for (int i = 0; i < visit.length; i++) {
				if (visit[i]) {
					sum+=arr[i];
				}
			}
			if(sum==S) {
				count++;
			}
			return;
		}

		visit[depth] = true;
		powerSet(depth + 1);

		visit[depth] = false;
		powerSet(depth + 1);

	}
}
