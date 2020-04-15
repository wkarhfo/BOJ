import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_네트워크연결 {
	static int N, M, a, b, c;
	static int[] arr;
	static List<Data> list;
	static int result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		arr = new int[N + 1];
		for (int i = 1; i < arr.length; i++) {
			arr[i] = i;
		}
		list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list.add(new Data(a, b, c));
		}

		Collections.sort(list, new Comparator<Data>() {

			@Override
			public int compare(Data o1, Data o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.count, o2.count);
			}
		});
		int count = N - 1;
		for (int i = 0; i < list.size(); i++) {
			int tempa = findSet(list.get(i).start);
			int tempb = findSet(list.get(i).end);
			if (tempa == tempb)
				continue;
			if (count == 0) {
				break;
			}
			uinion(tempa, tempb);
			result+=list.get(i).count;
			count--;
		}
		System.out.println(result);

	}

	private static void uinion(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if (a != b)
			arr[b] = a;
	}

	static int findSet(int num) {
		if (arr[num] == num)
			return num;
		int temp = findSet(arr[num]);
		arr[num] = temp;
		return temp;
	}

	static class Data {
		int start;
		int end;
		int count;

		public Data(int start, int end, int count) {
			super();
			this.start = start;
			this.end = end;
			this.count = count;
		}

	}
}
