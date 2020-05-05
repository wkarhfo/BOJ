import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_이분그래프 {
	static int V, E;
	static ArrayList<Integer>[] list;
	static int[] arr;
	static boolean flag;
	static int a;
	static int b;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			list = new ArrayList[V + 1];

			for (int i = 1; i <= V; i++) {
				list[i] = new ArrayList<>();
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}
			arr = new int[V + 1];// 1 or 2로 구분 하기 ... +visit
			flag = true;
			for (int i = 1; i <= V; i++) {
				if (arr[i] == 0) {
					arr[i] = 1; // 시작은 1
					dfs(i);
				}
			}
			if (flag) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	static void dfs(int idx) {
		for (int i = 0; i < list[idx].size(); i++) {
			if (arr[list[idx].get(i)] == 0) {
				arr[list[idx].get(i)] = arr[idx] * (-1);

				dfs(list[idx].get(i));
			} else if (arr[list[idx].get(i)] == arr[idx]) {
				flag = false;
				return;

			}
		}
	}

}
