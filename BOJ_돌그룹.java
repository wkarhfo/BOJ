import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_돌그룹 {
	static int A, B, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(A, B, C));
		boolean flag = false;
		int count=0;
		while (!q.isEmpty()) {
			count++;
			Data tmp = q.poll();
			if(count>=10000)break;
			int a = tmp.a;
			int b = tmp.b;
			int c = tmp.c;
			if (a == b && b == c) {
				flag = true;
				break;
			}

			if (a < b) {
				int tempa = a * 2;
				int tempb = b - a;
					q.add(new Data(tempa, tempb, c));
				
			} else if (a > b) {
				int tempa = a - b;
				int tempb = b * 2;
					q.add(new Data(tempa, tempb, c));
			}
			if (b < c) {
				int tempb = b * 2;
				int tempc = c - b;
					q.add(new Data(a, tempb, tempc));
			} else if (b > c) {
				int tempb = b - c;
				int tempc = c * 2;
					q.add(new Data(a, tempb, tempc));
			}
			if (a < c) {
				int tempa = a * 2;
				int tempc = c - a;
					q.add(new Data(tempa, b, tempc));
			} else if (a > c) {
				int tempa = a - c;
				int tempc = c * 2;
					q.add(new Data(tempa, b, tempc));
			}
		}
		if (flag) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	static class Data {
		int a;
		int b;
		int c;

		public Data(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

}
