import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_최소스패닝트리_크루스칼 {
	static int V, E;
	static ArrayList<Data> list;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		arr = new int[V + 1];
		list = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new Data(a, b, c));
		}

		Collections.sort(list, new Comparator<Data>() {

			@Override
			public int compare(Data o1, Data o2) {
				return Integer.compare(o1.cost, o2.cost);
			}
		});
		for (int i = 1; i < arr.length; i++)
			arr[i] = i;
		int count=0;
		int result=0;
		for (int i = 0; i < list.size(); i++) {
			int tempa = findset(list.get(i).start);
			int tempb = findset(list.get(i).dest);
			if(tempa==tempb)
				continue;
			
			if(count==V-1)
				break;
			union(tempa,tempb);
			result+=list.get(i).cost;
			count++;
		}
		System.out.println(result);
	}

	private static void union(int a, int b) {
		a=findset(a);
		b=findset(b);
		if(a!=b)
			arr[b]=a;
	}

	private static int findset(int num) {
		if(arr[num]==num)
			return num;
		return arr[num]=findset(arr[num]);
	}

	static class Data {
		int start;
		int dest;
		int cost;

		public Data(int start, int dest, int cost) {
			super();
			this.start = start;
			this.dest = dest;
			this.cost = cost;
		}

	}
}
