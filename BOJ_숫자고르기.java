import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BOJ_숫자고르기 {
	static int N;
	static int[] arr;
	static int count = 1;
	static Set<Integer> set;
	static int[] result;
	static List<Integer> list;
	static int MAX=0;
	static int[] last_result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N + 1];
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine().trim());
		}

		for (int i = 1; i <= N; i++) {
			result = new int[count];
			list = new ArrayList<>();
			dfs(1, 0);
			count++;
		}
		System.out.println(last_result.length);
		for(int i=0;i<last_result.length;i++) {
			System.out.println(last_result[i]);
		}
	}

	private static void dfs(int start, int depth) {
		if (depth == count) {

		
			set = new TreeSet<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o1, o2);
				}
			});
			
			
			for(int i=0;i<result.length;i++) {
				set.add(result[i]);
			}
			
			boolean flag=true;
			if(list.size()!=set.size()) {
				return;
			}else {
				int co=0;
				for(int num:set) {
					if(num!=list.get(co)) {
						flag=false;
						break;
					}
					co++;
				}
			
				
			}
			if(flag) {

				if(MAX<set.size()) {
					int co=0;
					last_result=new int[set.size()];
					for(int num:set) {
						last_result[co]=num;
						co++;
					}
				}
			}
			
			return;
		}
		for (int i = start; i < arr.length; i++) {
			result[depth] = arr[i];
			list.add(i);
			dfs(i + 1, depth + 1);
			list.remove(list.size() - 1);
		}
	}

}
