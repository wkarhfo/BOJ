import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_역사_시간초과 {
	static int n, k, s;
	static ArrayList<Integer>[] list;
	static boolean[] visit;
	static boolean[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		input = new boolean[n + 1];

		list = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			input[a] = true;
			input[b] = true;
		}

		s = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < s; i++) {
			visit = new boolean[n + 1];
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (!input[a]) {
				System.out.println(0);
			} else if (!input[b]) {
				System.out.println(0);
			} else {
				if (a > b) { // 나오는 답 반대로 출력
					Queue<Integer> q = new LinkedList<>();
					for (int k = 1; k < list[b].size(); k++) {
						q.add(list[b].get(k));
						visit[list[b].get(k)] = true;
					}
					boolean flag = false;
					while (!q.isEmpty()) {
						int temp = q.poll();
						if (temp == a) {
							flag = true;
						}
						for (int m = 1; m < list[temp].size(); m++) {
							if (visit[list[temp].get(m)])
								continue;

							visit[list[temp].get(m)] = true;
							q.add(list[temp].get(m));
						}

					}
					if (flag) {
						System.out.println(1);
					} else {
						System.out.println(0);
					}
				} else {
					Queue<Integer> q = new LinkedList<>();
					for (int k = 1; k < list[a].size(); k++) {
						q.add(list[a].get(k));
						visit[list[a].get(k)] = true;
					}
					boolean flag = false;
					while (!q.isEmpty()) {
						int temp = q.poll();
						if (temp == b) {
							flag = true;
						}
						for (int m = 1; m < list[temp].size(); m++) {
							if (visit[list[temp].get(m)])
								continue;

							visit[list[temp].get(m)] = true;
							q.add(list[temp].get(m));
						}

					}
					if (flag) {
						System.out.println(-1);
					} else {
						System.out.println(0);
					}
				}
			}
		}

	}

}
