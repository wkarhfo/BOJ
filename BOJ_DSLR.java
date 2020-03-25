import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_DSLR {
	static int A, B;
	static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			visit = new boolean[10000];
			Queue<Data> q = new LinkedList<>();
			q.add(new Data(A,""));
			visit[A] = true;

			while (!q.isEmpty()) {
				Data temp = q.poll();
				if(temp.x==B) {
					System.out.println(temp.result);
					break;
				}
				for (int k = 0; k < 4; k++) {
					int ax = 0;
					String type="";
					if (k == 0) {
						ax = (2 * temp.x) % 10000;
						type="D";
					} else if (k == 1) {
						if (temp.x == 0) {
							ax = 9999;
						} else {
							ax= temp.x-1;
						}
						type="S";
					} else if (k == 2) {

						String s = Integer.toString(temp.x);
						int[] arr = new int[4];
						int m = temp.x % 10; // 1자리
						int n = ((temp.x - m) / 10) % 10; // 10자리
						int l = ((((temp.x - m) / 10) - n) / 10) % 10; // 100자리
						int h = (((((temp.x - m) / 10) - n) / 10) - l) / 10; // 1000자리

						if (s.length() == 1) {
							arr[3] = m;
						} else if (s.length() == 2) {
							arr[2] = n;
							arr[3] = m;
						} else if (s.length() == 3) {
							arr[1] = l;
							arr[2] = n;
							arr[3] = m;
						} else {
							arr[0] = h;
							arr[1] = l;
							arr[2] = n;
							arr[3] = m;
						}

						// 왼쪽으로 자리바꿈
						int first = arr[0];
						for (int i = 0; i < 4; i++) {
							if (i == 3) {
								arr[i] = first;
							} else
								arr[i] = arr[i + 1];
						}

						ax = ((arr[0] * 10 + arr[1]) * 10 + arr[2]) * 10 + arr[3];
						type="L";
					} else {
						String s = Integer.toString(temp.x);
						int[] arr = new int[4];
						int m = temp.x % 10; // 1자리
						int n = ((temp.x - m) / 10) % 10; // 10자리
						int l = ((((temp.x - m) / 10) - n) / 10) % 10; // 100자리
						int h = (((((temp.x - m) / 10) - n) / 10) - l) / 10; // 1000자리

						if (s.length() == 1) {
							arr[3] = m;
						} else if (s.length() == 2) {
							arr[2] = n;
							arr[3] = m;
						} else if (s.length() == 3) {
							arr[1] = l;
							arr[2] = n;
							arr[3] = m;
						} else {
							arr[0] = h;
							arr[1] = l;
							arr[2] = n;
							arr[3] = m;
						}

						// 왼쪽으로 자리바꿈
						int last = arr[3];
						for (int i = 3; i >= 0; i--) {
							if (i == 0) {
								arr[i] = last;
							} else
								arr[i] = arr[i - 1];
						}

						ax = ((arr[0] * 10 + arr[1]) * 10 + arr[2]) * 10 + arr[3];
						type="R";
					}

					if (ax < 0 || ax >= 10000 || visit[ax] == true)
						continue;
					visit[ax] = true;
					q.add(new Data(ax,temp.result+type));

				}

			}

		}
	}
	static class Data{
		int x;
		String result;
		public Data(int x, String result) {
			super();
			this.x = x;
			this.result = result;
		}
	}
}
