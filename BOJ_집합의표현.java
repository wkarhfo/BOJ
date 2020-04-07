import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_집합의표현 {
	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if (type == 0) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			} else {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (findSet(a) == findSet(b)) {
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
			}
//			System.out.println(Arrays.toString(arr));
		}
		System.out.println(sb);
	}

	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if (a == b)
			return;
		else if (a < b)
			arr[b] = a;
		else
			arr[a]=b;

	}

	private static int findSet(int num) {
		if (arr[num] == num)
			return num;
		int temp = findSet(arr[num]);
		arr[num] = temp;
		return temp;
	}

}
