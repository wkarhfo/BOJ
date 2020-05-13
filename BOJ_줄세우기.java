import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_줄세우기 {
	static int N, M;
	static ArrayList<Integer>[] list;
	static int[] inDegree;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inDegree = new int[N + 1];
		result = new int[N + 1];
		list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			inDegree[b]++;
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0)
				q.add(i);
		}

		for (int i = 1; i <= N; i++) {
			if (q.isEmpty()) {
				return;
			}
			int x = q.poll();
			result[i] = x;
			for (int j = 0; j < list[x].size(); j++) {
				int y = list[x].get(j);
				if (--inDegree[y] == 0) {
					q.add(y);
				}
			}
		}
		
		for(int i=1;i<=N;i++)
			System.out.print(result[i]+" ");

	}
}
