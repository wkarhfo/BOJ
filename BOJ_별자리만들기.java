import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_별자리만들기 {
	static int n;
	static double[][] arr;
	static int[] reset;
	static double result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		arr = new double[n + 1][n + 1];
		reset = new int[n + 1];
		ArrayList<Edge> list = new ArrayList<>();
		list.add(new Edge(0, 0));

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double a = Double.parseDouble(st.nextToken());
			double b = Double.parseDouble(st.nextToken());
			list.add(new Edge(a, b));

		}
		ArrayList<Edge> li = new ArrayList<>();

		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				double temp = Math.pow(Math.abs(list.get(i).a - list.get(j).a), 2) + Math.pow(Math.abs(list.get(i).b - list.get(j).b),2);
				temp = Math.sqrt(temp);
				li.add(new Edge(i, j, temp));
			}
		}
		Collections.sort(li, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return Double.compare(o1.count, o2.count);
			}
		});

		for (int i = 1; i < reset.length; i++) {
			reset[i] = i;
		}
//		int cnt=0;
		for (int i = 0; i < li.size(); i++) {
			int tempa = findSet(li.get(i).x);
			int tempb = findSet(li.get(i).y);

			if (tempa == tempb)
				continue;

//			if(cnt==n-1)
//				break;
			union(tempa, tempb);
			result += li.get(i).count;
//			cnt++;
		}
		System.out.printf("%.2f\n", result);
	}

	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if (a != b)
			reset[b] = a;
	}

	static int findSet(int num) {
		if (reset[num] == num)
			return num;
		int temp = findSet(reset[num]);
		reset[num] = temp;
		return temp;
	}

	static class Edge {
		double a;
		double b;

		int x;
		int y;
		double count;

		public Edge(int x, int y, double count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}

		public Edge(double a, double b) {
			super();
			this.a = a;
			this.b = b;
		}

	}
}
